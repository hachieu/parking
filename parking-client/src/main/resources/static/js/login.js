$(document).ready(function(){
       	var form = $("#formLogin");
       	
       	var username = $("#username");
        var mess1 = $("#mess1");
       	
        var password = $("#password");
        var mess2 = $("#mess2");
       	
       	username.blur(checkUsername);
       	password.blur(checkPassword);
    
       	form.submit(function(){
            if(checkUsername() & checkPassword() ){
               return true;
            }
            else{
                return false;
            }
         });
         
	function checkUsername()
    {
        var user = $("#username").val();
        let filter = /^([A-Za-z]{1})+([A-Za-z0-9])+([@]{1})+([A-Za-z])+(?:\.[A-Za-z]+)+$/;
        if(!filter.test(user) || user.length == "")
        {
            username.addClass("error");
            mess1.text("Kiểm tra lại tên đăng nhập !");
            mess1.addClass("error");
            return false;
        }
        else
        {
            username.removeClass("error");
            mess1.text("");
            mess1.removeClass("error");
            return true;
        }
    }
    
    function checkPassword(){
    	
    	var pass = $("#password").val();
        if(pass.length == "" ){
            password.addClass("error");
            mess2.text("Kiểm tra lại mật khẩu !");
            mess2.addClass("error");
            return false;
        }
        else{
	        password.removeClass("error");
	        mess2.text("");
	        mess2.removeClass("error");
	        return true;
        }
    }
    
});