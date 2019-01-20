using System.Collections.Generic;
using SnappyPhone.Data.Entities;

namespace SnappyPhone.Data
{
  public interface ISnappyRepository
  {
    IEnumerable<Product> GetAllProducts();
    IEnumerable<Product> GetProductByCategory(string category);
    Product GetProductById(int id);

    IEnumerable<Order> GetAllOrders(bool includeItems);
    IEnumerable<Order> GetAllOrdersByUser(string username, bool includeItems);

    Order GetOrderById(string username, int id);

    IEnumerable<string> GetCategoriesAndSubcategories();

    void AddEntity(object model);
    void RemoveEntity(object model);
    bool SaveChanges();
  }
}