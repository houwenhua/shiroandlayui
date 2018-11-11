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
    <title>表单</title>
    <link rel="stylesheet" href="../../frame/layui2/css/layui.css">
    <link rel="stylesheet" href="../../frame/static/css/style.css">
    <link rel="icon" href="../../frame/static/image/code.png">
</head>
<body class="body">

<form class="layui-form" lay-filter="example" action="" id="userForm" onsubmit="return false">
    <div class="layui-form-item" style="display: none;">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id" lay-verify="required" autocomplete="off" placeholder="请输入账号" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">账号</label>
        <div class="layui-input-block">
            <input type="text" name="usercode" lay-verify="required" autocomplete="off" placeholder="请输入账号" class="layui-input layui-disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">盐巴</label>
        <div class="layui-input-block">
            <input type="text" name="salt" lay-verify="required" placeholder="请输入加盐方式" autocomplete="off" class="layui-input">
        </div>
    </div>
   <%-- <div class="layui-form-item">
        <label class="layui-form-label">是否锁定</label>
        <div class="layui-input-block">
            <input type="checkbox" name="locked" lay-skin="switch" lay-text="ON|OFF" lay-filter="switchLocked">
        </div>
    </div>--%>
   <%-- <div class="layui-form-item">
        <label class="layui-form-label">角色信息</label>
        <div class="layui-input-block">
            <select name="userrole" lay-filter="aihao" id="zcySelect" lay-verify="required">
                <option value=""></option>
            </select>
        </div>
    </div>--%>


    <div class="layui-form-item" style="display:none;">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1" id="btn-addtwo">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="../../frame/layui2/layui.js" charset="utf-8"></script>
<script>
    function child(obj){
        layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form
            //表单初始赋值
            form.val('example', {
                "id":obj.id,
                "usercode": obj.usercode, // "name": "value"
                "username": obj.username,
                "password": obj.password,
                "salt":obj.salt,//复选框选中状态
                //"locked":obj.locked === "0"?false:true, //开关状态
            });
            form.render();
        });
    }

    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate,
            $ = layui.jquery;


        //监听指定开关
        form.on('switch(switchLocked)', function(data){
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
           // layer.tips('温馨提示：为开(true)时代表用户锁定，不可登录使用。', data.othis)
        });


        //监听提交
        form.on('submit(demo1)', function(data){
            $.ajax({
                type:"post",
                dataType:"json",
                url:"/userController/update.action",
                data:{
                    id:$.trim(data.field.id),
                    //usercode:$.trim(data.field.usercode),
                    username:$.trim(data.field.username),
                    password:$.trim(data.field.password),
                    salt:$.trim(data.field.salt),
                    //locked:$.trim((data.field.locked)==="on"?1:0)
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