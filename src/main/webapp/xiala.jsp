<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/10/31
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/formSelects-v4.css" />
</head>
<body>
<div class="layui-form">
    <select name="city" xm-select="example5_5" xm-select-search="">
        <option value="">哈哈, 默认是没有的</option>
    </select>
</div>
</body>
<script src="frame/layui3/layui.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

    //全局定义一次, 加载formSelects
    layui.config({
        base: 'js/src/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });
    //加载模块
    layui.use(['jquery', 'formSelects'], function(){
        var formSelects = layui.formSelects;

        formSelects.config('example5_5', {
            searchUrl: 'http://yapi.demo.qunar.com/mock/9813/layui/search',
            success: function(id, url, searchVal, result){
                formSelects.value('example5_5', [result.data[0].value, result.data[4].value]);
            }
        });
    });
</script>
</html>
