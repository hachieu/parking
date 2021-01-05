$(document).ready(function()
{
       	var form = $("#addTicket");
       	
        var vehicle = $("#vehicle");
        var mess1 = $("#mess1");

        var price = $("#price");
        var mess2 = $("#mess2");
       	
       	vehicle.blur(checkVehicle);
        price.blur(checkPrice);
        
       	form.submit(function(){
            if(checkVehicle() & checkPrice()){
               return true;
            }
            else{
                return false;
            }
         });

    function checkVehicle(){
        
        var type = $("#vehicle").val();
        if(type.length == "" || type=="0"){
            vehicle.addClass("error");
            mess1.text("Chưa chọn loại vé !");
            mess1.addClass("error");
            return false;
        }
        else{
            vehicle.removeClass("error");
            mess1.text("");
            mess1.removeClass("error");
            return true;
        }
    }
         
	
    function checkPrice()
    {
        var number = $("#price").val();
        var filter = /^[0-9]+$/;
        if(!filter.test(number) || number <= 1000 || number > 5000000)
        {
            price.addClass("error");
            mess2.text("Kiểm tra lại giá vé !");
            mess2.addClass("error");
            return false;
        }
        else
        {
            price.removeClass("error");
            mess2.text("");
            mess2.removeClass("error");
            return true;
        }
    }
});

$(document).ready(function(){
    $('#back').click(function(){
        parent.history.back();
        return false;
    });
});

$(document).on("click", ".deleteticket", function() {
	var ticketid = $(this).data('id');
	 $('#formDelete').attr('action', '/parking/ticket/delete/' + ticketid);
});