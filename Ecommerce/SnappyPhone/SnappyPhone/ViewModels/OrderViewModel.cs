using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
using SnappyPhone.Data.Entities;

namespace SnappyPhone.ViewModels
{
    public class OrderViewModel
    {
      public int OrderId { get; set; }

      [Required]
      [MinLength(4)]
      public string OrderNumber { get; set; }

      public DateTime OrderDate { get; set; }

      public ICollection<OrderItemViewModel> Items { get; set; }
    }
}
