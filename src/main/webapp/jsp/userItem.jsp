<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/10/9
  Time: 11:16
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
    <title>Data-Table 表格</title>
    <link rel="stylesheet" href="../frame/layui/css/layui.css">
    <!--<link rel="stylesheet" href="http://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">-->
    <link rel="stylesheet" href="../frame/static/css/style.css">
    <link rel="icon" href="../frame/static/image/code.png">
</head>
<body class="body">

<!-- 工具集 -->
<div class="my-btn-box">
    <span class="fl">
        <a class="layui-btn layui-btn-danger radius btn-delect" id="btn-delete-all">批量删除</a>
        <a class="layui-btn btn-add btn-default" id="btn-add">添加</a>
        <a class="layui-btn btn-add btn-default" id="btn-refresh"><i class="layui-icon">&#x1002;</i></a>
    </span>
    <span class="fr">
        <span class="layui-form-label">搜索条件：</span>
        <div class="layui-input-inline">
            <input type="text" autocomplete="off" placeholder="请输入搜索条件" class="layui-input">
        </div>
        <button class="layui-btn mgl-20">查询</button>
    </span>
</div>

<!-- 表格 -->
<div id="dateTable" lay-filter="dateTable"></div>

<script type="text/javascript" src="../frame/layui/layui.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript">
    // layui方法
    layui.use(['table', 'form', 'layer', 'vip_table'], function () {

        // 操作对象
        var form = layui.form
            , table = layui.table
            , layer = layui.layer
            , vipTable = layui.vip_table
            , $ = layui.jquery;


    });

    // layui方法
    layui.use(['table', 'form', 'layer', 'vip_table'], function () {

        // 操作对象
        var form = layui.form
            , table = layui.table
            , layer = layui.layer
            , vipTable = layui.vip_table
            , $ = layui.jquery;

        // 表格渲染
        var tableIns = table.render({
            elem: '#dateTable',                  //指定原始表格元素选择器（推荐id选择器）
            height: vipTable.getFullHeight(),    //容器高度
            cols: [[                  //标题栏
                {checkbox: true, sort: true, fixed: true, space: true},
                {field: 'id', title: 'ID', width: 80},
                {field: 'usercode', title: '账号', width: 120},
                {field: 'username', title: '用户姓名', width: 120},
                {field: 'password', title: '密码', width: 180},
                {field: 'salt', title: '盐巴', width: 180},
                {field: 'locked', title: '是否锁定', width: 180},
                {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
            ]],
            id: 'dataCheck',
            url: '/userController/findAllUser.action',
            method: 'post',
            page: true,
            limits: [10, 20, 30, 40, 50],
            limit: 10 //默认采用30
        });


        // 刷新
        $('#btn-refresh').on('click', function () {
            tableIns.reload();
        });


        // you code ...
        //监听工具条
        table.on('tool(dateTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('真的删除该行么', function(index){
                    $.ajax({
                        url: "/userController/delete.action",
                        type: "POST",
                        data:{
                            "id":data.id,
                            "usercode":data.usercode
                        },
                        dataType: "json",
                        success: function(data){
                            if(data == '1'){
                                //删除这一行
                                obj.del();
                                //关闭弹框
                                layer.close(index);
                                layer.msg("删除成功", {icon: 6});
                            }else{
                                layer.msg("删除失败", {icon: 5});
                            }
                        }
                    });
                });
            } else if(obj.event === 'edit'){
                layer.alert('编辑行：<br>'+ JSON.stringify(data))
            }
        });

        $('#btn-add').on('click', function () {
            layer.open({
                type: 2,
                title: '用户添加',
                shadeClose: false,
                anim:0,//动画平滑放大。默认
                shade: 0.8,
                maxmin:true,
                btn:['保存','取消'],
                area: ['30%', '90%'],
                content: '/useradd.jsp', //iframe的url
                success: function(layero, index){
                    /*var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    iframeWin.addUser();*/
                },
                yes: function(index,layero){
                    var body = layer.getChildFrame('body', index);
                    var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                    iframeWin.addUser();
                },
                btn2: function(){
                    //alert("这是点击取消按钮走的回调");
                },
                end:function () {
                    tableIns.reload();
                }
            });
        });
    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="del">删除</a>
</script>
</body>
</html>
