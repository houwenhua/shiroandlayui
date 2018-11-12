<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/11/12
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<HEAD>
    <TITLE> ZTREE DEMO </TITLE>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../../frame/layui/css/layui.css">
    <link rel="stylesheet" href="../../frame/static/css/style.css">
    <link rel="icon" href="../../frame/static/image/code.png">

  <%--  <link rel="stylesheet" href="../../zTree/css/demo.css" type="text/css">--%>
    <link rel="stylesheet" href="../../zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="../../zTree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../../zTree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="../../zTree/js/jquery.ztree.excheck.js"></script>

</HEAD>
<BODY class="body">
<div>
    <input type="text" id="id" name="id" lay-verify="required" placeholder="请输入ID" autocomplete="off" class="layui-input layui-disabled">
    <div style="margin:0 auto;">
       <ul id="treeDemo" class="ztree" style="margin:0 auto;"></ul>
    </div>
</div>
</BODY>
<SCRIPT LANGUAGE="JavaScript">

    //加载菜单栏也在这个方法中，因为这种方式才能获取到角色id
    function child(obj){
        $("#id").val(obj.id);

        var setting = {
        view: {
            selectedMulti: false
        },
        check: {
            enable: true
        },
        async: {
            enable: true,
            url:"/roleController/findPermissionMenu.action?id=" + obj.id,
            autoParam:[],
            contentType: "application/json",
            otherParam:{},
            dataFilter: filter //异步获取的数据filter 里面可以进行处理  filter 在下面
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: ""
            }
        },//个人理解加上这个就能按级别显示，其中的id pid 对应你的实体类
        callback: {
            onAsyncSuccess: function() {
                //展开所有的节点
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                treeObj.expandAll(true);
            },
            onClick: function(treeId, treeNode) {
                var treeObj = $.fn.zTree.getZTreeObj(treeNode);
                var selectedNode = treeObj.getSelectedNodes()[0];
                $("#txtId").val(selectedNode.id);
                $("#txtAddress").val(selectedNode.name);
            }
        } //这里是节点点击事件
    };

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting);
        });
    }

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }



    function addRolePermission() {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var ids = "";
        for(var i=0;i<nodes.length;i++){
            ids = ids + "," + nodes[i].id;
        }
        $.ajax({
            url:"/roleController/addRolePermission.action",
            type:"post",
            data:{
                roleid:$("#id").val(),
                ids:ids.substr(1)
            },
            success:function (data) {
                if(data === "444"){
                    layer.msg("没有操作权限", {icon: 5});
                }else if(data === "1") {
                    //假设这是iframe页
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    parent.layer.msg("操作成功", {icon: 6});
                } else {
                    layer.msg("操作失败", {icon: 6});
                }
            }
        })
    }

</SCRIPT>
</HTML>
