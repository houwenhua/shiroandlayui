<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/10/12
  Time: 9:43
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
    <title>用户角色添加</title>
    <link rel="stylesheet" href="../frame/layui3/css/layui.css">
    <link rel="stylesheet" href="../frame/static/css/style.css">
    <link rel="icon" href="../frame/static/image/code.png">
    <link rel="stylesheet" href="css/public.css" media="all" />
</head>
<body class="body">

<form class="layui-form" lay-filter="example" action="" id="userForm" onsubmit="return false">
    <div class="layui-form-item" style="display: none;">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id" id="id" lay-verify="required" autocomplete="off" placeholder="请输入账号" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">账号</label>
        <div class="layui-input-block">
            <input type="text" name="usercode" id="usercode" lay-verify="required" autocomplete="off" placeholder="请输入账号" class="layui-input layui-disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="username" id="username" lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input layui-disabled">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-block">
            <select lay-filter="aaa" multiple name="userrole" id="userrole" lay-verify="required">
            </select>
        </div>
    </div>

    <div class="layui-form-item" style="display:none;">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1" id="btn-addtwo">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="./frame/layui3/layui.js"></script>

<script type="text/javascript" src="js/selectMultiple.js"></script>
<script>
    //填充表单
    function child(obj){
        layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form,
                $ = layui.jquery;

            $("#id").val(obj.id);
            $("#usercode").val(obj.usercode);
            $("#username").val(obj.username);
        });
    }


    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate,
            $ = layui.jquery;


        //加载下拉框的角色选择
        $.ajax({
            type:"POST",
            dataType:"json",
            url:"/userController/findAllRole.action",
            success:function(data) {
                for(var i = 0; i < data.length; i++){
                    $("#userrole").append("<option value=" + data[i].id + ">" + data[i].name + "</option>");
                }
                form.render();
            }
        });



        //监听提交
        form.on('submit(demo1)', function(data){
            alert(data.field.userrole);
            $.ajax({
                type:"post",
                dataType:"json",
                url:"/userController/addUserRoles.action",
                data:{
                    id:$.trim(data.field.id),
                    userrole:$.trim(data.field.userrole)
                },
                success:function (data) {
                    if(data === "444"){
                        layer.msg("没有操作权限", {icon: 5});
                    }else if(data === "1") {
                        //假设这是iframe页
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                        parent.layer.msg("添加成功", {icon: 6});
                    } else {
                        layer.msg("添加失败", {icon: 6});
                    }
                }
            })
        });
    });

    //父页面调用实现增加
    function addUser() {
        layui.use(['form', 'layedit', 'laydate'], function() {
            var $ = layui.jquery;
            //点击保存按钮
            $("#btn-addtwo").click();
        });


    }
</script>
</body>
</html>