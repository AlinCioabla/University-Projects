using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc.Rendering;
using SnappyPhone.Data.Entities;

namespace SnappyPhone.ViewModels
{
    public class ShopViewModel
    {
      public IEnumerable<Product> Products { get; set; }
      public string SearchString { get; set; }
      public string Filter { get; set; }
      public string SortBy { get; set; }
      public bool Ascending { get; set; }

      public SelectList Filters { get; set; }
    }
}
