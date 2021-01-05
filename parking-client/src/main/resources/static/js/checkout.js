$(document).ready(function()
{
       	var form = $("#checkout");
       	
        var licence = $("#licence");
        var mess1 = $("#mess1");
       	
       	licence.blur(checkLicence);
        
       	form.submit(function(){
            if(checkLicence()){
               return true;
            }
            else{
                return false;
            }
         });

    function checkLicence(){
        var filter = /^([1-9]{2})+([A-Z]{1})+([\-]{1})+([0-9]{2})+([\-])+([0-9]{5})+$/;
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
         
});

$(document).ready(function(){
    $('#back').click(function(){
        parent.history.back();
        return false;
    });
});

$(document).on("click", ".deletemonthlyticket", function() {
	var idm = $(this).data('id');
	 $('#formDelete').attr('action', '/monthlyout/delete/' + idm);
});

$(document).on("click", ".dailyout", function() {
	var idm = $(this).data('id');
	 $('#formDelete').attr('action', '/parking/checkout/delete/' + idm);
});