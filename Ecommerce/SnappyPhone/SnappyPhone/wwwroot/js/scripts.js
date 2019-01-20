$(document).ready(function () {

  $(document).on('change', '.btn-file :file', function () {
    var input = $(this),
      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [label]);
  });

  $('.btn-file :file').on('fileselect', function (event, label) {

    var input = $(this).parents('.input-group').find(':text'),
      log = label;
   
    var pictureid = formatPictureId(label);
    $('#pictureIdInput').val(pictureid);
  });
  function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function (e) {
        $('#imagePreview').attr('src', e.target.result);
      }

      reader.readAsDataURL(input.files[0]);
    }
  }

  $("#imageInput").change(function () {
    readURL(this);
  }); 

  function formatPictureId(filename){
    var lastDotPosition = filename.lastIndexOf(".");
    if (lastDotPosition !== -1) 
      filename = filename.substr(0, lastDotPosition);
    filename = filename.replace(" ", "_");

    return filename;
  }

});
