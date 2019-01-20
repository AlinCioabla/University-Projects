using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using SnappyPhone.Data.Entities;

namespace SnappyPhone.Data
{
  public class SnappyRepository : ISnappyRepository
  {
    private readonly SnappyContext _ctx;
    private readonly ILogger<SnappyRepository> _logger;

    public SnappyRepository(SnappyContext ctx, ILogger<SnappyRepository> logger)
    {
      _ctx = ctx;
      _logger = logger;
    }

    public IEnumerable<Product> GetAllProducts()
    {
      try
      {
        _logger.LogInformation("GetAllProducts was called");
        return _ctx.Products.OrderBy(p => p.Name).ToList();
      }
      catch(Exception ex)
      {
        _logger.LogInformation($"GetAllProducts failed: {ex}");
        return null;
      }
    }

    public IEnumerable<Product> GetProductByCategory(string category)
    {
      return _ctx.Products.Where(p => p.Category == category).ToList();
    }

    public Product GetProductById(int id)
    {
      return _ctx.Products.Where(p => p.Id == id).FirstOrDefault();
    }

    public IEnumerable<Order> GetAllOrders(bool includeItems)
    {
      if (includeItems)
      {
        return _ctx.Orders.Include(o => o.Items).ThenInclude(i => i.Product).ToList();
      }
      else
      {
        return _ctx.Orders.ToList();
      }
    }

    public IEnumerable<Order> GetAllOrdersByUser(string username, bool includeItems)
    {
      if (includeItems)
      {
        return _ctx.Orders.Where(o => o.User.UserName == username).Include(o => o.Items).ThenInclude(i => i.Product).ToList();
      }
      else
      {
        return _ctx.Orders.ToList();
      }
    }

    public Order GetOrderById(string username, int id)
    {
      return _ctx.Orders.Include(o => o.Items).ThenInclude(i => i.Product).Where(o => o.Id == id && o.User.UserName == username).FirstOrDefault();
    }

    public IEnumerable<string> GetCategoriesAndSubcategories()
    {
      var products = _ctx.Products.ToList();

      // Create a set to avoid duplicates
      var categories = new SortedSet<string>();
      foreach(var product in products)
      {
        categories.Add(product.Category);
        categories.Add(product.Subcategory);
      }

      return categories.ToList();
    }

    public void AddEntity(object model)
    {
      _ctx.Add(model);
    }

    public void RemoveEntity(object model)
    {
      _ctx.Remove(model);
    }

    public bool SaveChanges()
    {
      return _ctx.SaveChanges() > 0;
    }
  }
}

