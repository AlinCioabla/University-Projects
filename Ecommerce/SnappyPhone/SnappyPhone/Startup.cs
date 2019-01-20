using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using AutoMapper;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.IdentityModel.Tokens;
using Newtonsoft.Json;
using SnappyPhone.Data;
using SnappyPhone.Data.Entities;
using SnappyPhone.Services;

namespace SnappyPhone
{
  public class Startup
  {
    private readonly IConfiguration _config;

    public Startup(IConfiguration config)
    {
      _config = config;
    }

    // This method gets called by the runtime. Use this method to add services to the container.
    // For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=398940
    public void ConfigureServices(IServiceCollection services)
    {
      services.AddIdentity<StoreUser, IdentityRole>(cfg => { cfg.User.RequireUniqueEmail = true; })
        .AddEntityFrameworkStores<SnappyContext>();

      services.AddDbContext<SnappyContext>(cfg =>
      {
        cfg.UseSqlServer(_config.GetConnectionString("SnappyConnectionString"));
      });

      services.AddAuthentication().AddCookie().AddJwtBearer(cfg =>
      {
        cfg.TokenValidationParameters = new TokenValidationParameters()
        {
          ValidIssuer = _config["Tokens:Issuer"],
          ValidAudience = _config["Tokens:Audience"],
          IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_config["Tokens:Key"]))
        };
      });
      services.AddSession();

      services.AddTransient<SnappySeeder>();
      services.AddScoped<ISnappyRepository, SnappyRepository>();
      services.AddTransient<IMailService, NullMailService>();
      services.AddAutoMapper();

      services.AddMvc().SetCompatibilityVersion(CompatibilityVersion.Version_2_1)
        .AddJsonOptions(jsonOptions =>
          jsonOptions.SerializerSettings.ReferenceLoopHandling = ReferenceLoopHandling.Ignore);
    }

    // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
    public void Configure(IApplicationBuilder app, IHostingEnvironment env)
    {
      if (env.IsDevelopment())
      {
        app.UseDeveloperExceptionPage();
      }
      else
      {
        app.UseExceptionHandler("/error");
      }

      app.UseStaticFiles();
      app.UseSession();
      app.UseNodeModules(env);
      app.UseAuthentication();

      app.UseMvc(cfg =>
      {
        cfg.MapRoute("Default", "/{controller}/{action}/{id?}", new
        {
          controller = "App",
          action = "Index"
        });
      });
    }
  }
}
