using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SnappyPhone.ViewModels
{
  public class RegisterViewModel
  {
    [Required]
    [MaxLength(20)]
    public string Email { get; set; }

    [Required]
    [MaxLength(20)]
    public string Password { get; set; }

    [Required]
    [NotMapped] // Does not effect with your database
    [Compare("Password")]
    [MaxLength(20)]
    public string RepeatPassword { get; set; }
    
    [Required]
    public string FirstName { get; set; }

    [Required]
    public string LastName { get; set; }

  }
}
