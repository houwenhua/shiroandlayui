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
    <style>
        .layui-colla-content{
            border:1px solid #e2e2e2;
            margin-top:10px;
            /*padding-top:0px;*/
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">天气预报</div>
                        <div class="layui-card-body">
                            <div class="layui-colla-content layui-show">
                                <div id="main" style="height: 450px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-colla-content layui-show">
                            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                                <legend>成都今日温度变化</legend>
                            </fieldset>
                            <ul class="layui-timeline" id="weather">
                            </ul>
                        </div>
                        <%--<div class="layui-card-header">今日天气</div>
                        <div class="layui-card-body">

                        </div>--%>
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
        var dateNew = [];
        var tem1 = [];
        var tem2 = [];
        $.ajax({
            type: 'GET',
            url: 'https://www.tianqiapi.com/api/',
            data: 'version=v1&city=成都',
            dataType: 'JSON',
            error: function () {
                alert('网络错误');
            },
            success: function (res) {
                //var data = "";
                //循环获得横坐标
                for(var i = 0; i < res.data.length; i++) {
                    //data = data + "," + "'" + res.data[i].day + "'";
                    //dateNew.push(res.data[i].day);
                    dateNew[i] = res.data[i].day;
                    //获得最高温度
                    tem1.push(parseInt(res.data[i].tem1.substr(0,res.data[i].tem1.length - 1)));
                    //获得最低温度
                    tem2.push(parseInt(res.data[i].tem2.substr(0,res.data[i].tem2.length - 1)));
                }

                option = {
                    title : {
                        text: '未来七天成都气温变化',
                        subtext: '来自TianQiAPI.COM'
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
                            data : dateNew
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
                            data:tem1,//[11, 11, 15, 13, 12, 13, 10],
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
                            data:tem2,
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

                $("legend").text("今日"+res.city + " : " + res.data[0].wea);
                // 遍历数组
                for (var i = 0; i < res.data[0].hours.length; i++) {
                    $('#weather').append('<li class="layui-timeline-item"><i class="layui-icon layui-timeline-axis"></i><div class="layui-timeline-content layui-text"><div class="layui-timeline-title">'+ res.data[0].hours[i].day + ' : '+ res.data[0].hours[i].tem+'</div> </div></li>');
                }
            }
        });



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
