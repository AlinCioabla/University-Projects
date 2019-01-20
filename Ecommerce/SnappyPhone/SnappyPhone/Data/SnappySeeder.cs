using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity;
using Newtonsoft.Json;
using SnappyPhone.Data.Entities;

namespace SnappyPhone.Data
{
  public class SnappySeeder
  {
    private readonly SnappyContext _ctx;
    private readonly IHostingEnvironment _hosting;
    private readonly UserManager<StoreUser> _userManager;
    private readonly RoleManager<IdentityRole> _roleManager;

    public SnappySeeder(SnappyContext ctx, IHostingEnvironment hosting, UserManager<StoreUser> userManager, RoleManager<IdentityRole> roleManager)
    {
      _ctx = ctx;
      _hosting = hosting;
      _userManager = userManager;
      _roleManager = roleManager;
    }

    public async Task SeedAsync()
    {
      _ctx.Database.EnsureCreated();

      var adminRoleExists = await _roleManager.RoleExistsAsync("Admin");
      if (!adminRoleExists)
      {
        var result = await _roleManager.CreateAsync(new IdentityRole("Admin"));
        if (result != IdentityResult.Success)
        {
          throw new InvalidOperationException("Could not create the admin role.");
        }
      }

      var userRoleExists = await _roleManager.RoleExistsAsync("User");
      if (!userRoleExists)
      {
        var result = await _roleManager.CreateAsync(new IdentityRole("User"));
        if (result != IdentityResult.Success)
        {
          throw new InvalidOperationException("Could not create the user role.");
        }
      }

      StoreUser admin = await _userManager.FindByEmailAsync("alincioabla@gmail.com");
      if (admin == null)
      {
        admin = new StoreUser
        {
          UserName = "alincioabla@gmail.com",
          Email = "alincioabla@gmail.com",
          FirstName = "Alin",
          LastName = "Cioabla"
        };

        var addResult = await _userManager.CreateAsync(admin, "P@ssw0rd!");
        if (addResult != IdentityResult.Success)
        {
          throw new InvalidOperationException("Could not create the admin in seeder!");
        }

        var setRoleResult = await _userManager.AddToRoleAsync(admin, "Admin");
        if (setRoleResult != IdentityResult.Success)
        {
          throw new InvalidOperationException("Could not set the role admin in seeder!");
        }
      }

      StoreUser user = await _userManager.FindByEmailAsync("user123@gmail.com");
      if (user == null)
      {
        user = new StoreUser
        {
          UserName = "user123@gmail.com",
          Email = "user123@gmail.com",
          FirstName = "MrUser",
          LastName = "UserUser"
        };

        var addResult = await _userManager.CreateAsync(user, "P@ssw0rd!");
        if (addResult != IdentityResult.Success)
        {
          throw new InvalidOperationException("Could not create the user in seeder!");
        }

        var setRoleResult = await _userManager.AddToRoleAsync(user, "User");
        if (setRoleResult != IdentityResult.Success)
        {
          throw new InvalidOperationException("Could not set the role user in seeder!");
        }
      }

      if (!_ctx.Products.Any())
      {
        // Fill with sample data
        var filepath = Path.Combine(_hosting.ContentRootPath, "Data/sampledata.json");
        var json = File.ReadAllText(filepath);
        var products = JsonConvert.DeserializeObject<IEnumerable<Product>>(json);
        _ctx.Products.AddRange(products);

        var order = _ctx.Orders.Where(o => o.Id == 1).FirstOrDefault();
        if (order == null)
        {
          var items = new List<OrderItem>()
          {
            new OrderItem()
            {
              Product = products.First(),
              Quantity = 5,
              UnitPrice = products.First().Price
            }
          };

          var sampleOrder = new Order()
          {
            OrderDate = DateTime.UtcNow,
            OrderNumber = "1234",
            Items = items,
            User = user
          };

          _ctx.Orders.Add(sampleOrder);
        }

        _ctx.SaveChanges();
      }
    }
  }
}
