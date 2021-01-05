$(document).ready(function()
{
       	var form = $("#addParking");
       	
        var name = $("#name");
        var mess1 = $("#mess1");

        var area = $("#area");
        var mess2 = $("#mess2");
        
        var slot = $("#slot");
        var mess3 = $("#mess3");
       	
       	name.blur(checkName);
        area.blur(checkArea);
        slot.blur(checkSlot);
        
       	form.submit(function(){
            if(checkName() & checkArea() & checkSlot()){
               return true;
            }
            else{
                return false;
            }
         });

    function checkName(){
        var filter = /[^.,!`~@#$%^&*()+=?<>;:""''|{}\[\]\/\-\_\\]+$/;
        var nameParking = $("#name").val();
        if(!filter.test(nameParking) || nameParking.length > 20 || nameParking.length==""){
            name.addClass("error");
            mess1.text("Kiểm tra lại tên bãi đỗ xe !");
            mess1.addClass("error");
            return false;
        }
        else{
            name.removeClass("error");
            mess1.text("");
            mess1.removeClass("error");
            return true;
        }
    }
         
	
    function checkArea()
    {
        var number = $("#area").val();
        var filter = /^[0-9]+$/;
        if(!filter.test(number) || number==0 ||  number<0 || number > 2000000)
        {
            area.addClass("error");
            mess2.text("Kiểm tra lại diện tích bãi đỗ !");
            mess2.addClass("error");
            return false;
        }
        else
        {
            area.removeClass("error");
            mess2.text("");
            mess2.removeClass("error");
            return true;
        }
    }

    function checkSlot()
    {
        var number = $("#slot").val();
        var filter = /^[0-9]+$/;
        if(!filter.test(number) || number==0 || number<0 || number > 5000000)
        {
            slot.addClass("error");
            mess3.text("Kiểm tra lại sỗ chỗ của bãi đỗ !");
            mess3.addClass("error");
            return false;
        }
        else
        {
            slot.removeClass("error");
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

$(document).on("click", ".deleteparking", function() {
	var parkid = $(this).data('id');
	 $('#formDelete').attr('action', '/parking/park/delete/' + parkid);
});