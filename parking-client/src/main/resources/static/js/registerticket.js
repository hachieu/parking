$(document).ready(function()
{
       	var form = $("#registerMonthlyTicket");
       	
        var licence = $("#licence");
        var mess1 = $("#mess1");

        var customer = $("#customer");
        var mess2 = $("#mess2");

        var birthdate = $("#birthdate");
        var mess3 = $("#mess3");
        
        var typeticket = $("#typeticket");
        var mess5 = $("#mess5");
       	
       	licence.blur(checkLicence);
        customer.blur(checkCustomer);
        birthdate.blur(checkDate);
        typeticket.blur(checkTypeticket);
        
       	form.submit(function(){
            if(checkLicence() & checkCustomer() & checkDate() & checkTypeticket()){
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
         
	
    function checkCustomer(){
        var filter = /[^.,!`~@#$%^&*()+=?<>;:""''|{}\[\]\/\-\_\\0-9]+$/;
        var nameCustomer = $("#customer").val();
        if(!filter.test(nameCustomer) || nameCustomer.length == "" || nameCustomer.length>30){
            customer.addClass("error");
            mess2.text("Kiểm tra lại tên nhân viên !");
            mess2.addClass("error");
            return false;
        }
        else{
            customer.removeClass("error");
            mess2.text("");
            mess2.removeClass("error");
            return true;
        }
    }

    function checkDate()
    {
        var birthDate = $("#birthdate").val();
        if(birthDate.length == "")
        {
            birthdate.addClass("error");
            mess3.text("Kiểm tra lại ngày sinh khách hàng !");
            mess3.addClass("error");
            return false;
        }
        else
        {
            birthdate.removeClass("error");
            mess3.text("");
            mess3.removeClass("error");
            return true;
        }
    }

    function checkTypeticket(){
        
        var type = $("#typeticket").val();
        if(type.length == "" || type=="0"){
            typeticket.addClass("error");
            mess5.text("Kiểm tra lại loại vé xe !");
            mess5.addClass("error");
            return false;
        }
        else{
            typeticket.removeClass("error");
            mess5.text("");
            mess5.removeClass("error");
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

$(document).on("click", ".deleteregister", function() {
	var idregis = $(this).data('id');
	 $('#formDelete').attr('action', '/parking/register/delete/' + idregis);
});