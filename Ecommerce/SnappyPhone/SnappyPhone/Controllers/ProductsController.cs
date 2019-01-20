using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using SnappyPhone.Data;
using SnappyPhone.Data.Entities;

namespace SnappyPhone.Controllers
{
  public class ProductsController : Controller
  {
    private readonly ISnappyRepository _repository;
    private readonly ILogger<ProductsController> _logger;

    public ProductsController(ISnappyRepository repository, ILogger<ProductsController> logger)
    {
      _repository = repository;
      _logger = logger;
    }

    [Authorize(Roles = "Admin")]
    public IActionResult RemoveProduct(int productId)
    {
      var product = _repository.GetProductById(productId);

      if (product == null)
      {
        return NotFound("Cannot find the specified product.");
      }

      _repository.RemoveEntity(product);

      if (_repository.SaveChanges())
      {
        return RedirectToAction("AdminPage", "AdminPage");
      }

      return BadRequest("Cannot save the changes to the database.");
    }

    [Authorize(Roles = "Admin")]
    public IActionResult AddModifyProduct(int productId)
    {
      var product = _repository.GetProductById(productId);

      if (product == null)
      {
        product = new Product()
        {
          Id = 0,
          Category = "New Category",
          Subcategory = "New Subcategory",
          Name = "New Name",
          Price = 0,
          PictureId = "no-preview-available",
          Color = "New Color",
          Description = "New Description"
        };
      }

      return View(product);
    }

    [HttpPost]
    [Authorize(Roles = "Admin")]
    public IActionResult FinishProduct(Product product)
    {
      var existingProduct = _repository.GetProductById(product.Id);
      if (existingProduct != null)
      {
        _repository.RemoveEntity(existingProduct);
      }

      product.Id = 0;
      _repository.AddEntity(product);
      if (!_repository.SaveChanges())
      {
        return BadRequest("Cannot add the product to the database");
      }

      return RedirectToAction("AdminPage", "AdminPage");
    }

  }
}
