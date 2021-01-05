$(document).ready(function(){
    var form = $("#formSearch");

    var key = $("#key");
    var mess = $("#mess");

    key.blur(checkSearch);

    form.submit(function(){
         if(checkSearch()){
               return true;
            }
            else{
                return false;
            }
     });

    function checkSearch(){
        
        var input = $("#key").val();
        if(input.length == ""){
	         key.addClass("error");
	         mess.text("Nhập vào ô tìm kiếm !");
	         mess.addClass("error");
	         return false;
        }
        else{
            key.removeClass("error");
            mess.text("");
            mess.removeClass("error");
            return true;
        }
    }

});