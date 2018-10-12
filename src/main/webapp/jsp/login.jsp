<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/10/10
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <link rel="stylesheet" href="../frame/layui/css/layui.css">
    <link rel="stylesheet" href="../frame/static/css/style.css">
    <link rel="icon" href="../frame/static/image/code.png">
</head>
<body>

<div class="login-main">
    <header class="layui-elip">后台登录</header>
    <form class="layui-form" onsubmit="return false">
        <div class="layui-input-inline">
            <input type="text" name="usercode" id="usercode"  placeholder="账号" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="password" name="password" id="password"  placeholder="密码" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-input-inline login-btn">
            <button id="loginBtn" type="submit" class="layui-btn">登录</button>
        </div>
        <hr/>
        <%--<div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-primary">QQ登录</button>
        </div>
        <div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-normal">微信登录</button>
        </div>--%>
        <p><a href="javascript:;" class="fl">立即注册</a><a href="javascript:;" class="fr">忘记密码？</a></p>
    </form>
</div>


<script src="../frame/layui/layui.js"></script>
<script type="text/javascript">

    layui.use(['form'], function () {

        // 操作对象
        var form = layui.form
            , $ = layui.jquery;

        // you code ...
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


    });

</script>
</body>
</html>
