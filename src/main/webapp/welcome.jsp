<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/12
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./frame/layui/css/layui.css">
    <link rel="stylesheet" href="./frame/static/css/style.css">
    <link rel="icon" href="./frame/static/image/code.png">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">今日访问</div>
                        <div class="layui-card-body">
                            <div class="layui-colla-content layui-show" style="border-top: 1px solid #e2e2e2;">
                                <div id="main" style="height: 450px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">操作信息</div>
                <div class="layui-card-body layui-text">
                    <table class="layui-table" id="tab">
                        <colgroup>
                            <col width="100">
                            <col>
                        </colgroup>
                        <tbody>
                        <tr>
                            <th style="width: 150px;">日期</th>
                            <th>用户</th>
                            <th>操作</th>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="./frame/layui/layui.js"></script>
<script type="text/javascript" src="./frame/echarts/echarts.min.js"></script>
<script type="text/javascript">
    layui.use(['element', 'form', 'table', 'layer'], function () {
        var form = layui.form
            , table = layui.table
            , layer = layui.layer
            , element = layui.element
            , $ = layui.jquery;

        var myChart = echarts.init(document.getElementById('main'));


        // 指定图表的配置项和数据
        var base = +new Date(2014, 9, 3, 0);
        var oneDay = 600000;
        var date = [];
        var data = [];
        for (var i = 0; i < 144; i++) {
            var now = new Date(base += oneDay);
            date.push([now.getHours(), now.getMinutes()].join(':'));
            data.push(Math.floor(Math.random() * (1000 + 1 - 400) + 400));
        }
        date.unshift(0.0);
        data.unshift(500);
        option = {
            title : {
                text: '未来一周气温变化',
                subtext: '纯属虚构'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['最高气温','最低气温']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel : {
                        formatter: '{value} °C'
                    }
                }
            ],
            series : [
                {
                    name:'最高气温',
                    type:'line',
                    data:[11, 11, 15, 13, 12, 13, 10],
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                },
                {
                    name:'最低气温',
                    type:'line',
                    data:[1, -2, 2, 5, 3, 2, 0],
                    markPoint : {
                        data : [
                            {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name : '平均值'}
                        ]
                    }
                }
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    });
</script>
<script>
    layui.use(['element', 'form', 'table', 'layer'], function () {
        var $ = layui.jquery;
        //获得最新的15条日志信息
        $.ajax({
            url:"/visitController/findVisitLog.action",
            type:"post",
            dataType:"json",
            success:function (data) {
                var html = "";
                for(var i = 0; i < data.length ; i++) {
                    html = html + "<tr><td>"+data[i].visitdatetemp+"</td><td>"+data[i].username+"</td><td>"+data[i].opername+"</td></tr>";
                }
                $("#tab").append(html);
            }
        })
    });
</script>
</html>
