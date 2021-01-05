$(document).ready(function()
{
       	var form = $("#addEmployee");
       
        var name = $("#name");
        var mess2 = $("#mess2");

        var date = $("#date");
        var mess3 = $("#mess3");

        var gender = $('input[type="radio"]:checked')
        var mess4 = $("#mess4");

        var identity = $("#identity");
        var mess5 = $("#mess5");

        var phone = $("#phone");
        var mess6 = $("#mess6")
        
        var address = $("#address");
        var mess7 = $("#mess7");
       	
       	name.blur(checkName);
        date.blur(checkDate);
        // gender.blur(checkGender);
        identity.blur(checkIdentity);
       	phone.blur(checkPhone);
        address.blur(checkAdress);

       	form.submit(function(){
            if(checkName() & checkDate() & checkGender() & checkIdentity() & checkPhone() & checkAdress()){
               return true;
            }
            else{
                return false;
            }
         });
    
    function checkName(){
    	
    	var filter = /[^.,!`~@#$%^&*()+=?<>;:""''|0-9{}\[\]\/\-\_\\]+$/;
    	var nameEmp = $("#name").val();
        if(!filter.test(nameEmp) || nameEmp.length=="" || nameEmp.length>30){
            name.addClass("error");
            mess2.text("Kiểm tra lại tên nhân viên !");
            mess2.addClass("error");
            return false;
        }
        else{
	        name.removeClass("error");
	        mess2.text("");
	        mess2.removeClass("error");
	        return true;
        }
    }

    function checkDate()
    {
    	var birthDate = $("#date").val();
    	if(birthDate.length == "")
    	{
    		date.addClass("error");
	        mess3.text("Kiểm tra lại ngày sinh nhân viên !");
	        mess3.addClass("error");
	        return false;
    	}
    	else
    	{
    		date.removeClass("error");
	        mess3.text("");
	        mess3.removeClass("error");
	        return true;
    	}
    }

    function checkGender()
    {
    	var gender = $('input[type="radio"]:checked')
    	if(gender.length == 0)
    	{
    		// gender.addClass("error");
	        mess4.text("Bạn chưa chọn giới tính nhân viên !");
	        mess4.addClass("error");
	        return false;
    	}
    	else
    	{
    		// gender.removeClass("error");
	        mess4.text("");
	        mess4.removeClass("error");
	        return true;
    	}
    }

    function checkIdentity()
    {
        var number = $("#identity").val();
        var filter = /^[0-9]+$/;
        if(!filter.test(number) || number.length>10 || number.length<10)
        {
            identity.addClass("error");
            mess5.text("Kiểm tra lại số căn cước, phải nhập đủ 10 số !");
            mess5.addClass("error");
            return false;
        }
        else
        {
            identity.removeClass("error");
            mess5.text("");
            mess5.removeClass("error");
            return true;
        }
    }

    function checkPhone()
    {
       var number = $("#phone").val();
        var filter = /^(09|03|07|08|05|04)+([0-9]){8}$/;
        if(!filter.test(number))
        {
            phone.addClass("error");
            mess6.text("Kiểm tra lại số điện thoại !");
            mess6.addClass("error");
            return false;
        }
        else
        {
            phone.removeClass("error");
            mess6.text("");
            mess6.removeClass("error");
            return true;
        }
    }

    function checkAdress()
    {
    	var filter = /[^.,!`~@#$%^&*()+=?<>;:""''|{}\[\]\/\-\_\\]+$/;
    	var add = $("#address").val();
    	if(!filter.test(add) || add.length>100 || add.length=="")
    	{
    		address.addClass("error");
	        mess7.text("Bạn chưa nhập địa chỉ !");
	        mess7.addClass("error");
	        return false;
    	}
    	else
    	{
    		address.removeClass("error");
	        mess7.text("");
	        mess7.removeClass("error");
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

$(document).on("click", ".deleteemployee", function() {
	var idemp = $(this).data('id');
	 $('#formDelete').attr('action', '/parking/employee/delete/' + idemp);
});