<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/4/11
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Fly Template v3.0，基于 layui 的极简社区页面模版</title>
    <%--<meta http-equiv="refresh" content="10">--%><%--定时刷新间隔10秒--%>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="fly,layui,前端社区">
    <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
    <link rel="stylesheet" href="../../frame/layui3/css/layui.css">
    <link rel="stylesheet" href="../../js/fly/global.css">
</head>
<body>
<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12 content detail">
            <div class="fly-panel detail-box">
                <h1 id = "releaseTitle"></h1>
                <div class="fly-detail-info">
                    <!-- <span class="layui-badge">审核中</span> -->
                    <span class="layui-badge layui-bg-green fly-detail-column">动态</span>

                    <span class="layui-badge" style="background-color: #999;">未结</span>
                    <!-- <span class="layui-badge" style="background-color: #5FB878;">已结</span> -->

                    <span class="layui-badge layui-bg-black">置顶</span>
                    <span class="layui-badge layui-bg-red">精帖</span>

                    <div class="fly-admin-box" data-id="123">
                        <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>

                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->

                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> -->
                    </div>
                    <span class="fly-list-nums">
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i><span id="answerNum"></span></a>
            <i class="iconfont" title="人气">&#xe60b;</i> 99999
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="../user/home.html">
                        <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">
                    </a>
                    <div class="fly-detail-user">
                        <a href="../user/home.html" class="fly-link">
                            <cite id="sysName"></cite>
                            <i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
                            <i class="layui-badge fly-badge-vip">VIP3</i>
                        </a>
                        <span id="releaseDate"></span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                        <span style="padding-right: 10px; color: #FF7200">悬赏：60飞吻</span>
                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
                    </div>
                </div>
                <div class="detail-body photos" id="releaseContent">

                </div>
            </div>

            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>

                <ul class="jieda" id="jieda">
                  <%--  <li data-id="111" class="jieda-daan">
                        <a name="item-1111111111"></a>
                        <div class="detail-about detail-about-reply">
                            <a class="fly-avatar" href="">
                                <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt=" ">
                            </a>
                            <div class="fly-detail-user">
                                <a href="" class="fly-link">
                                    <cite>贤心</cite>
                                    <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                    <i class="layui-badge fly-badge-vip">VIP3</i>
                                </a>

                                <span>(楼主)</span>
                                <!--
                                <span style="color:#5FB878">(管理员)</span>
                                <span style="color:#FF9E3F">（社区之光）</span>
                                <span style="color:#999">（该号已被封）</span>
                                -->
                            </div>

                            <div class="detail-hits">
                                <span>2017-11-30</span>
                            </div>

                            <i class="iconfont icon-caina" title="最佳答案"></i>
                        </div>
                        <div class="detail-body jieda-body photos">
                            <p>香菇那个蓝瘦，这是一条被采纳的回帖</p>
                        </div>
                        <div class="jieda-reply">
              <span class="jieda-zan zanok" type="zan">
                <i class="iconfont icon-zan"></i>
                <em>66</em>
              </span>
                            <span type="reply">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span>
                            <div class="jieda-admin">
                                <span type="edit">编辑</span>
                                <span type="del">删除</span>
                                <!-- <span class="jieda-accept" type="accept">采纳</span> -->
                            </div>
                        </div>
                    </li>--%>

                  <%--  <li data-id="111">
                        <a name="item-1111111111"></a>
                        <div class="detail-about detail-about-reply">
                            <a class="fly-avatar" href="">
                                <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt=" ">
                            </a>
                            <div class="fly-detail-user">
                                <a href="" class="fly-link">
                                    <cite id="answerName">贤心</cite>
                                </a>
                            </div>
                            <div class="detail-hits">
                                <span id="answerDate">2017-11-30</span>
                            </div>
                        </div>
                        <div class="detail-body jieda-body photos">
                            <p id="answerContent">蓝瘦那个香菇，这是一条没被采纳的回帖</p>
                        </div>
                       &lt;%&ndash; <div class="jieda-reply">
              <span class="jieda-zan" type="zan">
                <i class="iconfont icon-zan"></i>
                <em>0</em>
              </span>
                            <span type="reply">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span>
                            <div class="jieda-admin">
                                <span type="edit">编辑</span>
                                <span type="del">删除</span>
                                <span class="jieda-accept" type="accept">采纳</span>
                            </div>
                        </div>&ndash;%&gt;
                    </li>
--%>
                    <!-- 无数据时 -->
                   <%-- <li class="fly-none">消灭零回复</li>--%>
                </ul>

                <div class="layui-form layui-form-pane">
                    <form onsubmit="return false">
                        <div class="layui-form-item layui-form-text">
                            <a name="comment"></a>
                            <div class="layui-input-block">
                                <textarea id="answerContent" name="answerContent" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="hidden" name="jid" value="123">
                            <button class="layui-btn" lay-filter="*" id="answerSubmit">提交回复</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
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
        ,base: '../../js/fly/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly', 'face'], function(){
        var $ = layui.$
            ,fly = layui.fly;
        //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
        /*
        $('.detail-body').each(function(){
          var othis = $(this), html = othis.html();
          othis.html(fly.content(html));
        });
        */
    });
</script>
<script type="text/javascript">
    function getQueryString(name) {
        var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

    var wtid = getQueryString("wtid");
    $("#answerNum").text(getQueryString("answerNum"));
    $.ajax({
        type:"POST",
        url:"/commentController/getWtreleaseDeteals.action",
        dataType:"json",
        data:{
            wtid:wtid
        },
        success:function (data) {
          $("#sysName").text(data.sysname);
          $("#releaseTitle").text(data.title);
          $("#releaseContent").text(data.content);
          $("#releaseDate").text(data.releasedate);
        }
    });

    //加载回帖
    $.ajax({
        type:"POST",
        url:"/commentController/getAllAnswerByWtId.action",
        dataType:"json",
        data:{
            wtid:wtid
        },
        success:function (data) {
            var html = '';
           for(i=0;i<data.length;i++) {

               html = html + ' <li data-id="111">\n' +
                   '        <a name="item-1111111111"></a>\n' +
                   '        <div class="detail-about detail-about-reply">\n' +
                   '        <a class="fly-avatar" href="">\n' +
                   '        <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt=" ">\n' +
                   '        </a>\n' +
                   '        <div class="fly-detail-user">\n' +
                   '        <a href="" class="fly-link">\n' +
                   '        <cite>'+ data[i].answername +'</cite>\n' +
                   '        </a>\n' +
                   '        </div>\n' +
                   '        <div class="detail-hits">\n' +
                   '        <span>'+ data[i].answerdate +'</span>\n' +
                   '        </div>\n' +
                   '        </div>\n' +
                   '        <div class="detail-body jieda-body photos">\n' +
                   '        <p>'+ data[i].answercontent +'</p>\n' +
                   '        </div>\n' +
                   '    </li>';
           }
           $("#jieda").append(html);
        }
    });


    $("#answerSubmit").click(function () {
        if($.trim($("#answerContent").val()) == "") {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.alert('内容不能为空',{icon: 5, title:'提示'});
            });
        }else {
            $.ajax({
                type:"POST",
                url:"/commentController/submitAnswerContent.action",
                dataType:"json",
                data:{
                    answerContent:$.trim($("#answerContent").val()),
                    wtid:wtid
                },
                success:function (data) {
                    if(data == "1") {
                        window.location.reload();
                        $("#answerContent").text(" ");
                    }else {
                        layui.use('layer', function(){
                            var layer = layui.layer;
                            layer.alert('回帖失败',{icon: 2, title:'提示'});
                        });
                    }
                }
            });
        }
    });

</script>
</body>
</html>