<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta id="viewport" name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <?php require_once 'handel.php';?>
    <script src="http://js.touclick.com/js.touclick?b=<?php echo $PUBKEY ?>" ></script>
</head>
<body >
     
    <form action="handel.php" method="POST" id="form">
        <input type="hidden" value="1" name="token" id="token"/> 
        <input type="hidden" value="2" name="checkAddress" id="checkAddress"/>
        <input type="hidden" value="3" name="checkCode" id="checkCode"/>
        用户名:<input type="text" id="username"/></br>
        密码:<input type="password" />
        
    </form>
    <input type="button" value="点击出现验证码" id="submit">
    <script> 
        TouClick.ready(function(){
            document.getElementById('submit').onclick = function(){
                
                TouClick().start({
                    modal:true,
                    position:"center",
                    fit:true, 
                    checkCode:document.getElementById("username").value,//可选 
                    onSuccess : function(obj){
                        document.getElementById("token").value = obj.token;
                        document.getElementById("checkAddress").value = obj.checkAddress;
                        document.getElementById("checkCode").value = document.getElementById("username").value;//可选
                        document.getElementById("form").submit();
                    }
                });
            }
        });
    </script>
</body>
</html>