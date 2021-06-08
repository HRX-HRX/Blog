<%--
  Created by IntelliJ IDEA.
  User: 小可爱
  Date: 2021/5/13
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>注册</title>
        <script src="jquery-1.9.1.min.js"></script>
        <script>
            function mysub(type) {
                // 拿到控件 username
                var username = jQuery("#username");
                var password = jQuery("#password");
                var password2 = jQuery("#password2");
                // 非空效验
                if(username.val().trim()==""){
                    alert("请先输入用户名！")
                    username.focus();
                    return false;
                }
                if(password.val().trim()==""){
                    alert("请先输入密码！")
                    password.focus();
                    return false;
                }
                if(password2.val().trim()==""){
                    alert("请先输入确认密码！")
                    password2.focus();
                    return false;
                }
                // 判断密码和确认密码是否一致
                if(password.val()!=password2.val()){
                    alert("两次输入的密码不一致！")
                    password.focus();
                    return false;
                }
                // 提交信息给后端
                jQuery.getJSON("reg",{
                    "username":username.val(),
                    "password":password.val()
                },function (data) {
                    if(data!=null && data.state===200){
                        // 说明请求成功
                        alert("恭喜：注册成功");
                        location.href = "login.html";
                    }else{
                        alert("抱歉：注册失败，请重试！");
                    }
                });
            }
        </script>
    </head>

    <body >
        <div>
            <h1>注册</h1>
            &nbsp;&nbsp;用户名：<input id="username" name="username" type="text"><p></p>
            &nbsp;&nbsp;新密码：<input id="password" name="password" type="password"><p></p>
            确认密码：<input id="password2" name="password2" type="password"><p></p>
            <input type="button" value=" 提交 " onclick="mysub(1)">
        </div>
    </body>
</html>
