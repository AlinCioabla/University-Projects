using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Mvc.ViewFeatures;
using SnappyPhone.Data;
using SnappyPhone.Data.Entities;
using SnappyPhone.Services;
using SnappyPhone.ViewModels;

namespace SnappyPhone.Controllers
{
  public class AppController : Controller
  {
    private readonly IMailService _mailService;
    private readonly ISnappyRepository _repository;

    public AppController(IMailService mailService, ISnappyRepository repository)
    {
      _mailService = mailService;
      _repository = repository;
    }

    public IActionResult Index()
    {
      return View();
    }

    [HttpGet("contact")]
    public IActionResult Contact()
    {
      return View();
    }

    [HttpPost("contact")]
    public IActionResult Contact(ContactViewModel model)
    {
      if (ModelState.IsValid)
      {
        // Send the email
        _mailService.SendMessage("alincioabla@gmail.com", model.Subject, $"From: {model.Name} - {model.Email} Message: {model.Message}");
        ViewBag.UserMessage = "Mail sent";
        ModelState.Clear();
      }
      else
      {
        // Show errors
      }
      return View();
    }

    public IActionResult About()
    {
      ViewBag.Title = "About";
      return View();
    }

    public IActionResult Shop(string searchString, string filter, string sortBy, bool ascending)
    {
      var products = _repository.GetAllProducts();

      if (!string.IsNullOrEmpty(filter))
      {
        products = products.Where(product => product.Category == filter || product.Subcategory == filter);
      }

      if (!string.IsNullOrEmpty(searchString))
      {
        products = products.Where(product => product.Name.ToLower().Contains(searchString.ToLower()));
      }

      if (!string.IsNullOrEmpty(sortBy))
      {
        switch (sortBy)
        {
          case "Price":
            products = products.OrderBy(product => product.Price);
            break;
          case "Category":
            products = products.OrderBy(product => product.Category);
            break;
          case "Subcategory":
            products = products.OrderBy(product => product.Subcategory);
            break;
          case "Name":
            products = products.OrderBy(product => product.Name);
            break;
          default:
            break;
        }

        if (!ascending)
          products = products.Reverse();
      }

      var shopVm = new ShopViewModel()
      {
        Products = products,
        Filters = new SelectList(_repository.GetCategoriesAndSubcategories()),
        Ascending = ascending,
        Filter = filter,
        SearchString = searchString,
        SortBy = sortBy
      };

      return View(shopVm);
    }

  }
}
