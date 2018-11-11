<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/10
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>表单</title>
    <link rel="stylesheet" href="../../frame/layui/css/layui.css">
    <link rel="stylesheet" href="../../frame/static/css/style.css">
    <link rel="icon" href="../../frame/static/image/code.png">
</head>
<body class="body">

<form class="layui-form" action="" id="userForm" onsubmit="return false">
   <%-- <div class="layui-form-item">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" id="id" name="id" lay-verify="required" placeholder="请输入ID" autocomplete="off" class="layui-input">
        </div>
    </div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">资源名</label>
        <div class="layui-input-block">
            <input type="text" id="name" name="name" lay-verify="required" placeholder="请输入资源名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-block">
            <input type="text" id="type" name="type" lay-verify="required" placeholder="请输入类型" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" id="url" name="url" lay-verify="required" placeholder="请输入地址" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">权限代码</label>
        <div class="layui-input-block">
            <input type="text" id="percode" name="percode" lay-verify="required" placeholder="请输入权限代码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">父ID</label>
        <div class="layui-input-block">
            <input type="text" id="parentid" name="parentid" lay-verify="required" placeholder="请输入父ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">父ID串</label>
        <div class="layui-input-block">
            <input type="text" id="parentids" name="parentids" lay-verify="required" placeholder="请输入父ID串" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">排序</label>
        <div class="layui-input-block">
            <input type="text" id="sortstring" name="sortstring" lay-verify="required" placeholder="请输入排序号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否启用</label>
        <div class="layui-input-block">
            <input type="checkbox" id="available" name="available" lay-skin="switch" lay-text="ON|OFF" lay-filter="switchLocked">
        </div>
    </div>

    <%--按钮--%>
    <div class="layui-form-item" style="display:none;">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1" id="btn-addtwo">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="../../frame/layui/layui.js" charset="utf-8"></script>
<script>
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
            /*layer.tips('温馨提示：为开(true)时代表用户锁定，不可登录使用。', data.othis)*/
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            $.ajax({
                type:"post",
                dataType:"json",
                url:"/permissionController/add.action",
                data:{
                    name:$.trim(data.field.name),
                    type:$.trim(data.field.type),
                    url:$.trim(data.field.url),
                    percode:$.trim(data.field.percode),
                    parentid:$.trim(data.field.parentid),
                    parentids:$.trim(data.field.parentids),
                    sortstring:$.trim(data.field.sortstring),
                    available:$.trim((data.field.available)==="on"?1:0)
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
            var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate,
                $ = layui.jquery;
            //点击保存按钮
            $("#btn-addtwo").click();
        });
    }
</script>
</body>
</html>
