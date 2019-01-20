using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using SnappyPhone.Data.Entities;

namespace SnappyPhone.Data
{
    public class SnappyContext: IdentityDbContext<StoreUser, IdentityRole, string>
    {
      public SnappyContext(DbContextOptions<SnappyContext> options): base(options)
      {
        
      }

      protected override void OnModelCreating(ModelBuilder modelBuilder)
      {
        base.OnModelCreating(modelBuilder);

        modelBuilder.Entity<Order>().HasData(new Order()
        {
          Id = 1,
          OrderDate = DateTime.UtcNow,
          OrderNumber = "12345"
        });
      }

      public DbSet<Product> Products { get; set; }
      public DbSet<Order> Orders { get; set; }
    }
}
