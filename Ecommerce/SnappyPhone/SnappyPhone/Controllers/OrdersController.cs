using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using SnappyPhone.Data;
using SnappyPhone.Data.Entities;
using SnappyPhone.ViewModels;

namespace SnappyPhone.Controllers
{
  [Route("api/[Controller]")]
  [ApiController]
  [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
  public class OrdersController : ControllerBase
  {
    private readonly ISnappyRepository _repository;
    private readonly ILogger<OrdersController> _logger;
    private readonly IMapper _mapper;
    private readonly UserManager<StoreUser> _userManager;

    public OrdersController(ISnappyRepository repository, ILogger<OrdersController> logger, IMapper mapper, UserManager<StoreUser> userManager)
    {
      _repository = repository;
      _logger = logger;
      _mapper = mapper;
      _userManager = userManager;
    }

    [HttpGet]
    public IActionResult Get(bool includeItems = true)
    {
      try
      {
        var username = User.Identity.Name;

        _logger.LogInformation("Getting all orders");
        var orders = _repository.GetAllOrdersByUser(username, includeItems);
        return Ok(_mapper.Map<IEnumerable<Order>, IEnumerable<OrderViewModel>>(orders));
      }
      catch (Exception e)
      {
        _logger.LogInformation($"Failed to get all orders: {e}");
        return BadRequest("Failed to get orders");
      }
    }

    [HttpGet("{id:int}")]
    public IActionResult Get(int id)
    {
      try
      {
        _logger.LogInformation($"Getting order with id {id}");
        var order = _repository.GetOrderById(User.Identity.Name, id);
        if (order != null)
        {
          return Ok(_mapper.Map<Order, OrderViewModel>(order));
        }
        return NotFound();
      }
      catch (Exception e)
      {
        _logger.LogInformation($"Failed to get order with id {id}: {e}");
        return BadRequest($"Failed to get order {id}");
      }
    }

    [HttpPost]
    public async Task<IActionResult> Post([FromBody]OrderViewModel model)
    {
      try
      {
        _logger.LogInformation($"Adding order {model} to the database.");

        if (ModelState.IsValid)
        {
          var newOrder = _mapper.Map<OrderViewModel, Order>(model);

          if (newOrder.OrderDate == DateTime.MinValue)
          {
            newOrder.OrderDate = DateTime.Now;
          }

          var currentUser = await _userManager.FindByNameAsync(User.Identity.Name);
          newOrder.User = currentUser;

          _repository.AddEntity(newOrder);
          if (_repository.SaveChanges())
          {
            var savedOrderViewModel = _mapper.Map<Order, OrderViewModel>(newOrder);
            return Created($"api/orders/{savedOrderViewModel.OrderId}", savedOrderViewModel);
          }
        }
        else
        {
          return BadRequest(ModelState);
        }

      }
      catch (Exception exception)
      {
        _logger.LogError($"Could not add the model {model} to the database. Error: {exception}");
      }

      return BadRequest($"Could not save the new order: {model}");
    }
  }
}
