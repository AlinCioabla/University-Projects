using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using SnappyPhone.Data;
using SnappyPhone.Data.Entities;

namespace SnappyPhone.Controllers
{
  public class AdminPageController : Controller
  {
    private readonly ISnappyRepository _repository;
    private readonly UserManager<StoreUser> _userManager;

    public AdminPageController(ISnappyRepository repository, UserManager<StoreUser> userManager)
    {
      _repository = repository;
      _userManager = userManager;
    }

    [Authorize(Roles = "Admin")]
    public IActionResult AdminPage()
    {
      ViewBag.Products = _repository.GetAllProducts();
      ViewBag.Orders = _repository.GetAllOrders(true);
      ViewBag.Users = _userManager.Users.ToList();

      return View();
    }
  }
}
