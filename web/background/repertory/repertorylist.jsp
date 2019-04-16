<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: diyanchao
  Date: 2019/3/27
  Time: 10:22
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
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="layui-anim layui-anim-up">
<div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <span class="x-right" style="line-height:40px">共有数据：${pageBean.count} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>图书序号</th>
            <th>图书名称</th>
            <th>图书类别</th>
            <th>库存</th>
            <th>操作</th></tr>
        </thead>
        <tbody>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${book.bookId}'><i class="layui-icon">&#xe605;</i></div>
                </td>
                <td>${book.bookId}</td>
                <td>${book.bookName}</td>
                <td>${book.typeId}</td>
                <td>${book.repertory}</td>
                <td class="td-manage">
                    <a title="编辑"  onclick="x_admin_show('编辑','<%=request.getContextPath()%>/RepertoryServlet?action=queryOne&bookId=${book.bookId}',600,400)" href="javascript:;">
                        <i class="layui-icon">&#xe642;</i>
                    </a>
                    <a title="删除" onclick="member_del(this,'${book.bookId}')" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page">
        <div>
            <a class="prev" href="">&lt;&lt;</a>
            <c:forEach var="i" begin="1" end="${pageBean.pages}" step="1">
                <c:if test="${i==pageBean.pageIndex}">
                    <span class="current">${i}</span>
                </c:if>
                <c:if test="${i!=pageBean.pageIndex}">
                    <a class="num" href="<%=request.getContextPath()%>/RepertoryServlet?action=query&pageIndex=${i}">${i}</a>
                </c:if>
            </c:forEach>
            <a class="next" href="">&gt;&gt;</a>
        </div>
    </div>

</div>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/RepertoryServlet",
                data:"action=delete&kid="+id,
                success:function (msg) {
                    //发异步删除数据
                    $(obj).parents("tr").remove();
                    if (msg==1){
                        layer.msg('删除成功!',{icon:1,time:1000});
                    } else{
                        layer.msg('已删除或不存在!',{icon:2,time:1000});
                    }
                }
            })
        });
    }



    function delAll (argument) {

        var data = tableCheck.getData();
        if (data==""){
            layer.msg('请至少选择1条数据');
            return;
        }
        layer.confirm('确认要删除这些信息吗？',function(index){
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/RepertoryServlet",
                data:"action=deleteAll&ids="+data,
                success:function (msg) {
                    if (msg>0){
                        //捉到所有被选中的，发异步进行删除
                        layer.msg('成功删除'+msg+'条数据', {icon: 1});
                    } else{
                        layer.msg('已删除或不存在!',{icon:1,time:1000});
                    }
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                }
            })
        });
    }
</script>
</body>
</html>