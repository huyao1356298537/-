<%--
  Created by IntelliJ IDEA.
  User: Geng xing
  Date: 2019/3/21
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>三味书屋</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <%@include file="../commons/info.jsp"%>

</head>

<body>
<div class="x-body">
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="bookName" class="layui-form-label">
                <span class="x-red">*</span>图书名称
            </label>
            <div class="layui-input-inline">
                <input value="${book.bookName}" type="text" id="bookName" name="bookName" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="author" class="layui-form-label">
                <span class="x-red">*</span>作者
            </label>
            <div class="layui-input-inline">
                <input  value="${book.author}" type="text" id="author" name="author" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="price" class="layui-form-label">
                <span class="x-red">*</span>价格
            </label>
            <div class="layui-input-inline">
                <input value="${book.price}" type="text" id="price" name="price" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="sellcount" class="layui-form-label">
                <span class="x-red">*</span>销量
            </label>
            <div class="layui-input-inline">
                <input value="${book.sellcount}" type="text" id="sellcount" name="sellcount" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="repertory" class="layui-form-label">
                <span class="x-red">*</span>库存
            </label>
            <div class="layui-input-inline">
                <input value="${book.repertory}" type="text" id="repertory" name="repertory" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="typeId2" class="layui-form-label">
                <span class="x-red">*</span>类型Id
            </label>
            <div class="layui-input-inline">
                <input  value="${book.typeId}" type="text" id="typeId2" name="typeId2" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="imagepath" class="layui-form-label">
                <span class="x-red">*</span>图片
            </label>
            <div class="layui-input-inline">
                <input  value="${book.imagepath}" type="text" id="imagepath" name="imagepath" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="publicationDate" class="layui-form-label">
                <span class="x-red">*</span>出版时间
            </label>
            <div class="layui-input-inline">
                <input   value="${book.publicationDate}" type="text" id="publicationDate" name="publicationDate" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="issale" class="layui-form-label">
                <span class="x-red">*</span>是否打折
            </label>
            <div class="layui-input-inline">
                <input value="${book.issale}" type="text" id="issale" name="issale" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="press" class="layui-form-label">
                <span class="x-red">*</span>出版社
            </label>
            <div class="layui-input-inline">
                <input value="${book.press}" type="text" id="press" name="press" required="" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label  class="layui-form-label">
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
            var author = data.field.author;
            var price = data.field.price;
            var repertory = data.field.repertory;
            var sellcount = data.field.sellcount;
            var typeId = data.field.typeId2;
            var imagepath = data.field.imagepath;
            var publicationDate = data.field.publicationDate;
            var issale = data.field.issale;
            var press = data.field.press;

            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/BookServlet",
                data:{
                    "action":"update",
                    "bookId":bookId,
                    "bookName":bookName,
                    "author":author,
                    "price":price,
                    "repertory":repertory,
                    "sellcount":sellcount,
                    "typeId":typeId,
                    "imagepath":imagepath,
                    "publicationDate":publicationDate,
                    "issale":issale,
                    "press":press
                },
                success:function (msg) {
                    if(msg=="1"){
                        //发异步，把数据提交给java
                        layer.alert("修改成功", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            window.parent.location.reload();
                        });
                    }else {
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

