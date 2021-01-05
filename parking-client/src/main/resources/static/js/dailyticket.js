$(document).ready(function()
{
       	var form = $("#dailyticket");
       	
        var licence = $("#licence");
        var mess1 = $("#mess1");

        /*var typeticket = $("#typeticket");
        var mess2 = $("#mess2");*/

        var parking = $("#parking");
        var mess3 = $("#mess3");
       	
       	licence.blur(checkLicence);
        //typeticket.blur(checkTypeTicket)
        parking.blur(checkParking);
        
       	form.submit(function(){
            if(checkLicence() & checkParking()){
               return true;
            }
            else{
                return false;
            }
         });

    function checkLicence(){
        var filter = /^([1-9]{1})+([0-9]{1})+([A-Z]{1})+([\-]{1})+([0-9]{2})+([\-])+([0-9]{5})+$/;
        var licence_plate = $("#licence").val();
        if(!filter.test(licence_plate) || licence_plate.length == ""){
            licence.addClass("error");
            mess1.text("Kiểm tra lại, bạn nhập theo định dạng 00(A-Z)-00-00000.");
            mess1.addClass("error");
            return false;
        }
        else{
            licence.removeClass("error");
            mess1.text("");
            mess1.removeClass("error");
            return true;
        }
    }
         
	/*function checkTypeTicket(){
        var choose = $("#typeticket").val();
        if(choose.length == "" || choose=='0'){
            typeticket.addClass("error");
            mess2.text("Chưa chọn loại vé !");
            mess2.addClass("error");
            return false;
        }
        else{
            typeticket.removeClass("error");
            mess2.text("");
            mess2.removeClass("error");
            return true;
        }
    }*/

    function checkParking(){
        
        var choose = $("#parking").val();
        if(choose.length == "" || choose=='0'){
            parking.addClass("error");
            mess3.text("Chưa chọn bãi đỗ xe !");
            mess3.addClass("error");
            return false;
        }
        else{
            parking.removeClass("error");
            mess3.text("");
            mess3.removeClass("error");
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

$(document).on("click", ".deletedaily", function() {
	var idd = $(this).data('id');
	 $('#formDelete').attr('action', '/parking/checkin/delete/' + idd);
});