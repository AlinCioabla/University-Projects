using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using SnappyPhone.Data.Entities;
using SnappyPhone.ViewModels;

namespace SnappyPhone.Data
{
    public class SnappyMappingProfile: Profile
    {
      public SnappyMappingProfile()
      {
        CreateMap<Order, OrderViewModel>()
          .ForMember(viewModel => viewModel.OrderId, vm => vm.MapFrom(order => order.Id))
          .ReverseMap();

        CreateMap<OrderItem, OrderViewModel>()
          .ReverseMap();
      }
    }
}
