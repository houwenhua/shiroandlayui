<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/4/10
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>基于 layui 的极简社区页面模版</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <link rel="stylesheet" href="../../frame/layui3/css/layui.css">
    <link rel="stylesheet" href="../../js/fly/global.css">
</head>
<body>
<div class="fly-header fly-panel fly-column">
    <div class="layui-container">
        <div class="fly-column-right layui-hide-xs">
            <span class="fly-search"><i class="layui-icon"></i></span>
            <%--<a href="pladd.jsp" class="layui-btn">发表新帖</a>--%>
            <button onclick="openAddWtRelease();" class="layui-btn">发表新帖</button>
        </div>
        <%--<div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
            <a href="pladd.jsp" class="layui-btn">发表新帖</a>
        </div>--%>
    </div>
</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="fly-panel" style="margin-bottom: 0;">
                <div class="fly-panel-title fly-filter">
                    <a href="" class="layui-this">综合</a>
                    <span class="fly-mid"></span>
                    <a href="">未结</a>
                    <span class="fly-mid"></span>
                    <a href="">已结</a>
                    <span class="fly-mid"></span>
                    <a href="">精华</a>
                    <span class="fly-filter-right layui-hide-xs">
            <a href="" class="layui-this">按最新</a>
            <span class="fly-mid"></span>
            <a href="">按热议</a>
          </span>
                </div>

                <ul class="fly-list">
                    <c:forEach items="${requestScope.wtList }" var="wt">
                        <li>
                               <a href="user/home.html" class="fly-avatar">
                                   <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">
                               </a>
                               <h2>
                                   <a class="layui-badge">动态</a>
                                   <input type="hidden" value="${wt.wtid}">
                                   <a href="/jsp/pl/pldetail.jsp?wtid=${wt.wtid}" <%--onclick="openDetail(${wt.wtid});"--%>>${wt.title}</a>
                               </h2>
                               <div class="fly-list-info">
                                   <a href="user/home.html" link>
                                       <cite>${wt.sysname}</cite>
                                       <!--
                                       <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                       <i class="layui-badge fly-badge-vip">VIP3</i>
                                       -->
                                   </a>
                                   <span>${wt.releasedate}</span>

                                   <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
                                   <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
                                   <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> 66
              </span>
                               </div>
                               <div class="fly-list-badge">
                                   <!--<span class="layui-badge layui-bg-red">精帖</span>-->
                               </div>
                           </li>
                    </c:forEach>
                </ul>

                <!-- <div class="fly-none">没有相关数据</div> -->
                <div style="text-align: center">
                    <div class="laypage-main"><span class="laypage-curr">1</span><a href="/jie/page/2/">2</a><a href="/jie/page/3/">3</a><a href="/jie/page/4/">4</a><a href="/jie/page/5/">5</a><span>…</span><a href="/jie/page/148/" class="laypage-last" title="尾页">尾页</a><a href="/jie/page/2/" class="laypage-next">下一页</a></div>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="../../frame/layui3/layui.js"></script>
<script>
    layui.cache.page = 'jie';
    layui.cache.user = {
        username: '游客'
        ,uid: -1
        ,avatar: '../../res/images/avatar/00.jpg'
        ,experience: 83
        ,sex: '男'
    };
    layui.config({
        version: "3.0.0"
        ,base: '../../res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>
<script>
    function openAddWtRelease() {
        layui.use(['table', 'form', 'layer'], function () {
            var form = layui.form
                , table = layui.table
                , layer = layui.layer
                /*, vipTable = layui.vip_table*/
                , $ = layui.jquery;

            layer.open({
            type: 2,
            title: '发表问题',
            shadeClose: true,
         /*   anim:0,//动画平滑放大。默认
            shade: 0.8,*/
            maxmin:true,
         /*   btn:['保存','取消'],*/
            area: ['100%', '100%'],
            content: '/jsp/pl/pladd.jsp', //iframe的url
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
    });
    }

    function openDetail(wtid) {
        //alert(wtid);
        layui.use(['table', 'form', 'layer'], function () {
            var form = layui.form
                , table = layui.table
                , layer = layui.layer
                /*, vipTable = layui.vip_table*/
                , $ = layui.jquery;

            layer.open({
                type: 2,
                shadeClose: true,
                maxmin:true,
                area: ['100%', '100%'],
                content: '/jsp/pl/pldetail.jsp', //iframe的url
                success: function(layero, index){
                    layer.setTop(layero);
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
        });
    }
</script>

</body>
</html>
