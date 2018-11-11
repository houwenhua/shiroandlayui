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
    <link rel="stylesheet" href="css/formSelects-v4.css" />
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
            <select lay-filter="userrole" multiple name="userrole" id="userrole" lay-verify="required" xm-select="userrole" xm-select-search="">
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
<script>

    var userid;

    //全局定义一次, 加载formSelects
    layui.config({
        base: 'js/src/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });


    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate,
            $ = layui.jquery;


        //监听提交
        form.on('submit(demo1)', function(data){

            //alert(data.field.userrole);
            /*var userroles = data.field.userrole.split(",");

            var roleids = "";
            $.ajax({
                type:"POST",
                dataType:"json",
                url:"/userController/findUserAllRole.action",
                data:{
                    id:userid
                },
                success:function(role) {
                    for(var i = 0; i < role.length; i++){
                        for(var j = 0; j < userroles.length; j++){
                            //判断用户本身具有的角色如何和下拉框选中的一样，就什么都不做
                            if(role[i] != userroles[j]) {
                                roleids = roleids + "," + userroles[j];
                            }
                        }
                    }
                    alert(roleids);


                    //判断是增加角色还是删除角色还是没有改变
                    //form.render();
                }
            });*/
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
            });
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

    //填充表单
    function child(obj){
        userid = obj.id;
        layui.use(['form', 'layedit', 'laydate', 'formSelects'], function() {
            var form = layui.form,
                $ = layui.jquery,
                formSelects = layui.formSelects;

            $("#id").val(obj.id);
            $("#usercode").val(obj.usercode);
            $("#username").val(obj.username);

            formSelects.data('userrole', 'server', {
                url: '/userController/findAllRole.action',
                type:'post',
                data:{
                    id:obj.id
                },
                beforeSuccess: function(id, url, searchVal, result){
                    //我要把数据外层的code, msg, data去掉
                    result = result.data;
                    //然后返回数据
                    return result;
                },
                success: function(id, url, searchVal, result){
                    console.log('id: example6_3, 成功返回数据!!!');
                }
            });

           /* formSelects.config('userrole', {
                searchUrl: '/userController/findAllRole.action',
                type:'post',
                success: function(id, url, searchVal, result){
                    //console.log(result.data);
                    //formSelects.value('userrole', [result.data[0].value]);
                    var data = "";
                    for(var i = 0; i < result.data.length; i++) {
                        data += result.data[i].value + ",";
                    }
                    data = data.substr(0,data.length-1);
                    data = result.data[0].value;
                    formSelects.value('userrole', [data]);
                }
            });

            formSelects.config('example5_5', {
                searchUrl: 'http://yapi.demo.qunar.com/mock/9813/layui/search',
                success: function(id, url, searchVal, result){
                    formSelects.value('example5_5', [result.data[0].value, result.data[4].value]);
                }
            });
*/
            /*formSelects.config('userrole', {
                searchUrl: '/userController/findUserAllRole.action',
                type:'post',
                data:{
                    id:obj.id
                },
                success: function(id, url, searchVal, result){
                    console.log(result);
                    var data = result.data;
                    //var json =
                    for(var i = 0; i < data.length; i++) {
                        formSelects.value('userrole', data[i].value);
                    }
                    //formSelects.value('example5_5', [result.data[0].value, result.data[4].value]);
                }
            });*/
            //$("#abc").val(["2","3"]);
            //$("#userrole").val(["2","3"]);
            //$("#userrole").val("spgly");
            //form.render();
            //$("select[name=userrole]").val("3");
            /*$.ajax({
                type:"POST",
                dataType:"json",
                url:"/userController/findUserAllRole.action",
                data:{
                    id:obj.id
                },
                success:function(data) {
                    for(var i = 0; i < data.length; i++){
                        //$("#userrole").val(data[i]);
                    }
                    form.render();
                }
            });*/
        });

    }
</script>
</body>
</html>