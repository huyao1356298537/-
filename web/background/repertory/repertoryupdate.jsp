<%--
  Created by IntelliJ IDEA.
  User: diyanchao
  Date: 2019/3/28
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/info.jsp"%>
<html>

<head>
    <meta charset="UTF-8">
    <title>库存管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<%--    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="../../static/css/font.css">
    <link rel="stylesheet" href="../../static/css/xadmin.css">--%>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../../static/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../static/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="bookName" class="layui-form-label">
                <span class="x-red">*</span>图书名称
            </label>
            <div class="layui-input-inline">
                <input value="${book.bookName}" type="text" id="bookName" name="bookName"  readonly lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="typeId1" class="layui-form-label">
                <span class="x-red">*</span>图书类别
            </label>
            <div class="layui-input-inline">
                <input value="${book.typeId}" type="text" id="typeId1"  name="typeId1" readonly
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="repertory" class="layui-form-label">
                <span class="x-red">*</span>库存
            </label>
            <div class="layui-input-inline">
                <input value="${book.repertory}" type="text" id="repertory" name="repertory"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">
            </label>
            <button  class="layui-btn" lay-filter="update" lay-submit="">
                修改
            </button>
        </div>
        <input value="${book.bookId}" type="hidden" id="bookId" name="bookId" required="" lay-verify="required"
               autocomplete="off" class="layui-input">
    </form>
</div>



<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;
        //监听提交
        form.on('submit(update)', function(data){
            var bookId = data.field.bookId;
            var bookName = data.field.bookName;
            var typeId = data.field.typeId1;
            var repertory = data.field.repertory;
            console.log(data)

            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/RepertoryServlet",
                data:{
                    "action":"update",
                    "bookId":bookId,
                    "bookName":bookName,
                    "typeId":typeId,
                    "repertory":repertory
                },
                success:function(msg){
                    alert(msg);
                    if(msg=="1"){
                        //发异步，把数据提交给java
                        layer.alert("修改成功", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            window.parent.location.reload();
                        });
                    }else{
                        layer.msg("信息不存在，修改失败")
                    }
                },
                error:function () {
                    layer.msg("修改异常")
                }
            })
            return false;
        });
    });
</script>
</body>

</html>