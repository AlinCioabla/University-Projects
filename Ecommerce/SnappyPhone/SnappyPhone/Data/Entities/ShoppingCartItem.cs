﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SnappyPhone.Data.Entities
{
    public class ShoppingCartItem
    {
      public Product Product { get; set; }

      public int Quantity { get; set; }
    }
}
