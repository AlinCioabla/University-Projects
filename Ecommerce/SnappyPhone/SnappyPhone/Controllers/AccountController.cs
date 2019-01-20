using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using Microsoft.IdentityModel.Tokens;
using SnappyPhone.Data.Entities;
using SnappyPhone.ViewModels;

namespace SnappyPhone.Controllers
{
  public class AccountController : Controller
  {
    private readonly ILogger<AccountController> _logger;
    private readonly SignInManager<StoreUser> _signInManager;
    private readonly UserManager<StoreUser> _userManager;
    private readonly IConfiguration _config;

    public AccountController(ILogger<AccountController> logger, SignInManager<StoreUser> signInManager,
      UserManager<StoreUser> userManager, IConfiguration config)
    {
      _logger = logger;
      _signInManager = signInManager;
      _userManager = userManager;
      _config = config;
    }

    public IActionResult Login()
    {
      if (this.User.Identity.IsAuthenticated)
      {
        return RedirectToAction("Index", "App");
      }

      return View();
    }

    public IActionResult Register()
    {
      if (this.User.Identity.IsAuthenticated)
      {
        return RedirectToAction("Index", "App");
      }

      return View();
    }

    [HttpPost]
    public async Task<IActionResult> Register(RegisterViewModel model)
    {
      if (ModelState.IsValid)
      {
        var user = new StoreUser()
        {
          FirstName = model.FirstName,
          LastName = model.LastName,
          Email = model.Email,
          UserName = model.Email
        };

        var registerResult = await _userManager.CreateAsync(user, model.Password);
        if (registerResult != IdentityResult.Success)
        {
          ModelState.AddModelError("", "Register failed");
        }
        else
        {
          var setRoleResult = await _userManager.AddToRoleAsync(user, "User");
          if (setRoleResult != IdentityResult.Success)
          {
            ModelState.AddModelError("", "Register failed - cannot add role.");
          }
          else
          {
            return RedirectToAction("Shop", "App");
          }
        }
      }

      return View();
    }

    [HttpPost]
    public async Task<IActionResult> Login(LoginViewModel model)
    {
      if (ModelState.IsValid)
      {
        var loginResult = await _signInManager.PasswordSignInAsync(model.Username, model.Password, model.RememberMe, false);

        if (loginResult.Succeeded)
        {
          if (Request.Query.Keys.Contains("ReturnUrl"))
          {
            return Redirect(Request.Query["ReturnUrl"].First());
          }
          else
          {
            return RedirectToAction("Shop", "App");
          }
        }
      }

      ModelState.AddModelError("", "Login failed");

      return View();
    }

    [HttpGet]
    public async Task<IActionResult> Logout()
    {
      await _signInManager.SignOutAsync();
      return RedirectToAction("Index", "App");
    }

    [HttpPost]
    public async Task<IActionResult> CreateToken([FromBody] LoginViewModel model)
    {
      if (ModelState.IsValid)
      {
        var user = await _userManager.FindByNameAsync(model.Username);
        if (user != null)
        {
          var result = await _signInManager.CheckPasswordSignInAsync(user, model.Password, false);

          if (result.Succeeded)
          {
            // Create the token
            var claims = new[]
            {
              new Claim(JwtRegisteredClaimNames.Sub, user.Email),
              new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString()),
            };

            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_config["Tokens:Key"]));
            var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

            var token = new JwtSecurityToken(
              _config["Tokens:Issuer"],
              _config["Tokens:Audience"],
              claims,
              expires: DateTime.UtcNow.AddMinutes(30),
              signingCredentials: creds);

            var results = new
            {
              token = new JwtSecurityTokenHandler().WriteToken(token),
              expiration = token.ValidTo
            };

            return Created("", results);
          }
        }
      }
      return BadRequest();

    }

    [Authorize(Roles = "Admin")]
    public async Task<IActionResult> RemoveUser(string userId)
    {
      var user = await _userManager.FindByIdAsync(userId);
      if (user == null)
      {
        return NotFound("Cannot find the user with the id: " + userId);
      }

      var result = await _userManager.DeleteAsync(user);
      if (result != IdentityResult.Success)
      {
        return BadRequest("Failed to delete the user with the id: " + userId);
      }

      return RedirectToAction("AdminPage", "AdminPage");
    }
  }
}
