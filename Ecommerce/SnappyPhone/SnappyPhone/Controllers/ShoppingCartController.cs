using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using SnappyPhone.Data;
using SnappyPhone.Data.Entities;
using SnappyPhone.Helpers;

namespace SnappyPhone.Controllers
{
  public class ShoppingCartController : Controller
  {
    private readonly ISnappyRepository _repository;
    private readonly UserManager<StoreUser> _userManager;

    public ShoppingCartController(ISnappyRepository repository, UserManager<StoreUser> userManager)
    {
      _repository = repository;
      _userManager = userManager;
    }

    public IActionResult Cart()
    {
      var cart = SessionHelper.GetObjectFromJson<List<ShoppingCartItem>>(HttpContext.Session, "cart");
      if (cart == null)
      {
        cart = new List<ShoppingCartItem>();
      }

      ViewBag.cart = cart;
      ViewBag.total = cart.Sum(item => item.Product.Price * item.Quantity);
      return View();
    }

    public IActionResult AddToCart(int productId)
    {
      // Get the cart from the current session
      var cart = SessionHelper.GetObjectFromJson<List<ShoppingCartItem>>(HttpContext.Session, "cart");
      var product = _repository.GetProductById(productId);

      if (product == null)
      {
        return NotFound("The product with the given id does not exist");
      }

      // Create a new cart if it doesn't exist
      if (cart == null)
      {
        cart = new List<ShoppingCartItem>();
      }

      int productIndexInCart = ExistsInCart(cart, productId);
      if (productIndexInCart != -1)
      {
        cart[productIndexInCart].Quantity++;
      }
      else
      {
        // Add to cart
        cart.Add(new ShoppingCartItem { Product = product, Quantity = 1 });
      }

      SessionHelper.SetObjectAsJson(HttpContext.Session, "cart", cart);

      return RedirectToAction("Shop", "App");
    }

    public IActionResult Remove(int productId)
    {
      List<ShoppingCartItem> cart =
        SessionHelper.GetObjectFromJson<List<ShoppingCartItem>>(HttpContext.Session, "cart");
      int index = ExistsInCart(cart, productId);
      if (index == -1)
      {
        return NotFound("The product was not found in the cart.");
      }

      cart.RemoveAt(index);
      SessionHelper.SetObjectAsJson(HttpContext.Session, "cart", cart);
      return RedirectToAction("Cart", "ShoppingCart");
    }

    [Authorize]
    public async Task<IActionResult> SendOrder()
    {
      List<ShoppingCartItem> cart =
        SessionHelper.GetObjectFromJson<List<ShoppingCartItem>>(HttpContext.Session, "cart");

      var user = await _userManager.FindByNameAsync(User.Identity.Name);

      var order = new Order()
      {
        User = user,
        OrderNumber = Guid.NewGuid().ToString(),
        OrderDate = DateTime.UtcNow,
        Id = 0
      };

      // Create the OrderItems
      var orderItems = new List<OrderItem>();
      foreach (var cartItem in cart)
      {
        var item = new OrderItem()
        {
          Order = order,
          Product = cartItem.Product,
          Quantity = cartItem.Quantity,
          UnitPrice = cartItem.Product.Price,
          Id = 0
        };

        orderItems.Add(item);
      }

      order.Items = orderItems;
      _repository.AddEntity(order);

      if (!_repository.SaveChanges())
      {
        return BadRequest("Cannot save the order.");
      }

      return RedirectToAction("Index", "App");
    }

    private int ExistsInCart(List<ShoppingCartItem> cart, int productId)
    {
      for (int i = 0; i < cart.Count; i++)
      {
        if (cart[i].Product.Id.Equals(productId))
        {
          return i;
        }
      }
      return -1;
    }
  }
}
