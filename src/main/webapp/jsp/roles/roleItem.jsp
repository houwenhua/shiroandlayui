<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/3
  Time: 21:59
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
    <link rel="stylesheet" href="../../frame/layui3/css/layui.css">
    <!--<link rel="stylesheet" href="http://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">-->
    <link rel="stylesheet" href="../../frame/static/css/style.css">
    <link rel="icon" href="../../frame/static/image/code.png">
    <style>
        .layui-form-switch{
            width:50px;
            margin-top: 0px !important;
        }

       /* .layui-table-view{
            width: 50%;
            display: inline-block;
        }*/
    </style>
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
            <input id="name" name="name" type="text" autocomplete="off" placeholder="请输入用户姓名" class="layui-input">
        </div>
        <button class="layui-btn mgl-20" id="btn-query">查询</button>
    </span>
</div>

<!-- 表格 -->
<table id="dateTable" lay-filter="dateTable"></table>
<%--<div style="width:45%;height: 500px;display: inline-block;background: red;margin-bottom: 16px;"></div>--%>

<script type="text/javascript" src="../../frame/layui3/layui.js"></script>
<%--<script type="text/javascript" src="../../js/index.js"></script>--%>
<script type="text/javascript">
    // layui方法
    layui.use(['table', 'form', 'layer'], function () {
        // 操作对象
        var form = layui.form,
            table = layui.table,
            layer = layui.layer,
           /* --vipTable = layui.vip_table,*/
            $ = layui.jquery;

            //alert($(window).height());
        // 表格渲染
        var tableIns = table.render({
            elem: '#dateTable',                  //指定原始表格元素选择器（推荐id选择器）
            height: $(window).height() - ( $('.my-btn-box').outerHeight(true) ? $('.my-btn-box').outerHeight(true) + 35 :  40 ),    //容器高度
            cols: [[                  //标题栏
                {checkbox: true, sort: true, fixed: true, space: true},
                {field: 'id', title: 'ID',width:100},
                {field: 'name', title: '名称',width:100},
                /*{field: 'available',align: 'left', title: '是否可用',width:100,templet:
                        '<div class="layui-form-item">\n' +
                        '        <div class="layui-input-block" style="margin-left:0">\n' +
                        '            <input type="checkbox" id="available" name="available" lay-skin="switch" lay-text="ON|OFF" lay-filter="switchLocked">\n' +
                        '        </div>\n' +
                        '    </div>'
                },*/
                {field:'available', title:'是否可用', width:100, templet: '#switchTpl'},
                {fixed: 'right', title: '操作',width:200, align: 'center', toolbar: '#barOption'} //这里的toolbar值是模板元素的选择器
            ]],
            id: 'dataCheck',
            url: '/roleController/findAllRole.action',
            method: 'post',
            page: true,
            limits: [10, 20, 30, 40, 50],
            limit: 10, //默认采用30
            done: function (res, curr, count) {
               /* var data = res.data;
                for(var i = 0; i < data.length; i++) {
                    if(data[i].id === "yhgly") {// && data[i].userrole === "用户管理员"
                        alert(data[i].id);
                        $("#del").addClass("layui-btn-disabled");
                        $("#del").click(function () {
                            return false;
                        })
                    }
                }*/
            }
        });

        //查询方法
        $("#btn-query").click(function () {
            var name=$("#name").val();
            tableIns.reload({
                method:'post',
                where:{
                    name:$.trim(name)
                }
            });
        });

        // 刷新
        $('#btn-refresh').on('click', function () {
            tableIns.reload();
        });

        //监听工具条
        table.on('tool(dateTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.open({
                    type: 2,
                    title: data.name,
                    shadeClose: false,
                    anim:0,//动画平滑放大。默认
                    shade: 0.8,
                    maxmin:true,
                    btn:['保存','取消'],
                    area: ['30%', '90%'],
                    content: 'rolePermission.jsp', //iframe的url
                    success: function(layero, index){
                        var iframe = window['layui-layer-iframe' + index];
                        //传递选中行的id值
                        iframe.child(data);
                    },
                    yes: function(index,layero){
                        var body = layer.getChildFrame('body', index);
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        iframeWin.addRolePermission();
                    },
                    btn2: function(){
                        //alert("这是点击取消按钮走的回调");
                    },
                    end:function () {
                        tableIns.reload();
                    }
                });
            } else if(obj.event === 'del'){
                if(data.id === "yhgly") {
                    layer.msg('系统管理员角色不能删除！！！',{icon: 5});
                } else {
                    layer.confirm('真的删除该行么',{icon: 5}, function(index){
                        $.ajax({
                            url: "/roleController/delete.action",
                            type: "POST",
                            data:{
                                "id":data.id,
                            },
                            dataType: "json",
                            success: function(data){
                                if(data === "444"){
                                    layer.msg("没有操作权限", {icon: 5});
                                }else if(data == '1'){
                                    //删除这一行
                                    obj.del();
                                    //关闭弹框
                                    layer.close(index);
                                    layer.msg("删除成功", {icon: 6});
                                }else if(data == '555'){
                                    layer.msg("该角色已经被使用，不能删除", {icon: 5});
                                }else{
                                    layer.msg("删除失败", {icon: 5});
                                }
                            }
                        });
                    });
                }
            } else if(obj.event === 'edit') {
                layer.open({
                    type: 2,
                    title: '角色修改',
                    shadeClose: false,
                    anim:0,//动画平滑放大。默认
                    shade: 0.8,
                    maxmin:true,
                    btn:['保存','取消'],
                    area: ['30%', '90%'],
                    content: 'roleupdate.jsp', //iframe的url
                    success: function(layero, index){
                        var iframe = window['layui-layer-iframe' + index];
                        //传递选中行的id值
                        iframe.child(data);
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
            }
        });

        //监听指定开关
        form.on('switch(switchLocked)', function(data){
            /*layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });*/
            // 获取当前控件
            var selectIfKey=data.othis;
            // 获取当前所在行
            var parentTr = selectIfKey.parents("tr");
            layer.tips('温馨提示：状态为'+ (this.checked ? 'true' : 'false'), data.othis);
            $.ajax({
                url:'/roleController/updateAvailable.action',
                type:'post',
                data:{
                    id:data.value,
                    available:this.checked ? '1' : '0'
                },
                success:function(data) {
                    if(data === "1") {
                        layer.msg("修改成功", {icon: 6});
                    }else if(data == '555'){

                        var switchIfNull=$(parentTr).find("td:eq(3)").find("div:eq(1)");
                        // 修改div的样式为F的样式,F的值
                        switchIfNull.prop("class","layui-unselect layui-form-switch layui-form-onswitch");//F的样式
                        switchIfNull.find("em").text("开");

                        layer.msg("该角色已经被使用，不能被关闭！！！", {icon: 5});
                    }
                }
            })
        });

        $('#btn-add').on('click', function () {
            layer.open({
                type: 2,
                title: '角色添加',
                shadeClose: false,
                anim:0,//动画平滑放大。默认
                shade: 0.8,
                maxmin:true,
                btn:['保存','取消'],
                area: ['30%', '90%'],
                content: 'roleadd.jsp', //iframe的url
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

        $("#btn-delete-all").click(function () {
            //获得所有的ID值组装成一，为分割的字符串
            var checkStatus = table.checkStatus('dataCheck');
            var ids = "";
            for(var i = 0; i < checkStatus.data.length; i++) {
                if(checkStatus.data[i].id === "yhgly") {
                    layer.msg('系统管理员用户不能删除！！！',{icon: 5});
                    return false;
                }
            }
            for(var i = 0; i < checkStatus.data.length; i++) {
                ids = ids + "," + checkStatus.data[i].id;
            }
            //去掉最前面的，逗号
            ids = ids.substr(1);
            if(ids != "") {
                layer.confirm('确定删除选中的数据?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        url: "/roleController/batchDelete.action",
                        type: "POST",
                        data:{
                            "ids":ids
                        },
                        dataType: "json",
                        success: function(data){
                            if(data === "444"){
                                layer.msg("没有操作权限", {icon: 5});
                            }else if(data == '1'){
                                tableIns.reload();
                                //关闭弹框
                                layer.close(index);
                                layer.msg("删除成功", {icon: 6});
                            }else if(data == '555'){
                                layer.msg("该角色已经被使用，不能删除", {icon: 5});
                            }else{
                                layer.msg("删除失败", {icon: 5});
                            }
                        }
                    });
                });
            } else {
                layer.msg("没有选中数据!!!",{icon:5});
            }
        });
    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-mini" lay-event="detail">操作资源</a>
    <a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="del" id="del">删除</a>
</script>
<script type="text/html" id="switchTpl">
    <input type="checkbox" name="available" value="{{d.id}}" lay-skin="switch" lay-text="开|关" lay-filter="switchLocked" {{ d.available === '1' ? 'checked' : '' }}>
</script>
</body>
</html>
