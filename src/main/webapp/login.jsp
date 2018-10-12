<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/13
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login.css">
    <title>登陆</title>
</head>
<body>
<main class="main" role="main">
    <div class="container">
        <div class="row">
            <div class="offset-md-3 col-md-6">
                <form class="form-container" onsubmit="return false">
                    <h2>管理员-登陆</h2>
                    <div class="form-group">
                        <label for="usercode">用户名</label>
                        <input type="input" name="usercode" class="form-control" id="usercode" aria-describedby="emailHelp" placeholder="请输入用户名">
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberme" id="rememberme">
                        <label class="form-check-label" for="exampleCheck1">记住我</label>
                    </div>
                    <button type="button" class="btn btn-success btn-block" id="loginBtn">登陆</button>
                </form>
            </div>
        </div>
    </div>
</main>
<div class="footer">
    <div class="container">
        Copyright &copy;2018 by HWH
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="./frame/layui/layui.js"></script>
<script>
    $("#loginBtn").click(function () {
        if($.trim($("#usercode").val()) == "") {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.alert('账号不能为空',{icon: 5, title:'提示'});
            });
        } else  if($.trim($("#password").val()) == "") {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.alert('密码不能为空',{icon: 5, title:'提示'});
            });
        } else {
            $.ajax({
                type:"POST",
                url:"/loginController/subLogin.action",
                dataType:"json",
                data:{
                    usercode:$.trim($("#usercode").val()),
                    password:$.trim($("#password").val())
                },
                success:function (data) {
                   if(data == "1") {
                       window.location.href = "/index.jsp";
                   }else {
                       layui.use('layer', function(){
                           var layer = layui.layer;
                           layer.alert('账号或者密码错误',{icon: 2, title:'提示'});
                       });
                   }
                }
            });
        }
    });

   /* $(function () {
        //记住密码功能
        var str = getCookie("loginInfo");
        str = str.substring(1,str.length-1);
        var usercode = str.split(",")[0];
        var password = str.split(",")[1];
        $("#usercode").val(usercode);
        $("#password").val(password);
        if( str != null && str != "") {
           $("input[type='checkbox']").attr("checked", true);
        }
    })
    //获取cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');//loginInfo="lisi,123456"
        for(var i=0; i<ca.length; i++) {
            var c = ca[i];

            while (c.charAt(0)==' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) != -1){
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }*/
</script>
</body>
</html>
