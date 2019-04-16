<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 张尧俊
  Date: 2019/3/29
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/info.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>三味书屋</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
</head>

<body>
<%--<div class="x-nav">--%>
      <%--<span class="layui-breadcrumb">--%>
        <%--<a href="">首页</a>--%>
        <%--<a href="">演示</a>--%>
        <%--<a>--%>
          <%--<cite>导航元素</cite></a>--%>
      <%--</span>--%>
    <%--<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">--%>
        <%--<i class="layui-icon" style="line-height:30px">ဂ</i></a>--%>
<%--</div>--%>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <span class="x-right" style="line-height:40px">共有数据：${pageBean.count}  条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>图书编号</th>
            <th>图书名称</th>
            <th>作者</th>
            <th>价格</th>
            <th>是否打折</th>
            <th>类型编号</th>
            <th>图片名称</th>
            <th>出版时间</th>
            <th>销量</th>
            <th>库存</th>
            <th>出版社</th>
            <th >操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookList}" var="bookList">
        <tr>
            <td>
                <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${bookList.bookId}'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>${bookList.bookId}</td>
            <td>${bookList.bookName}</td>
            <td>${bookList.author}</td>
            <td>${bookList.price}</td>
            <td>${bookList.issale}</td>
            <td>${bookList.typeId}</td>
            <td>${bookList.imagepath}</td>
            <td>${bookList.publicationDate}</td>
            <td>${bookList.sellcount}</td>
            <td>${bookList.repertory}</td>
            <td>${bookList.press}</td>
            <td class="td-manage">
                <a title="查看"  onclick="x_admin_show('编辑','<%=request.getContextPath()%>/BookServlet?action=queryOneBook&kid=${bookList.bookId}')" href="javascript:;">
                    <i class="layui-icon">&#xe63c;</i>
                </a>
                <a title="删除" onclick="member_del(this,'${bookList.bookId}')" href="javascript:;">
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
                    <a class="num" href="<%=request.getContextPath()%>/BookServlet?action=query&pageIndex=${i}">${i}</a>
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
                url:"<%=request.getContextPath()%>/BookServlet",
                data:"action=delete&kid="+id,
                success:function(msg){
                    //发异步删除数据
                    $(obj).parents("tr").remove();
                    if(msg==1){
                        layer.msg('删除成功!',{icon:1,time:1000});
                    }else{
                        layer.msg('已删除或不存在!',{icon:1,time:1000});
                    }
                }
            })
        });
    }

    function delAll (argument) {

        var data = tableCheck.getData();
        if(data==""){
            layer.msg('请至少选择1条数据');
            return;
        }
        layer.confirm('确认要删除这些信息吗？',function(index){
            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>/BookServlet",
                data:"action=deleteAll&ids="+data,
                success:function(msg){
                    if(msg>0){
                        //捉到所有被选中的，发异步进行删除
                        layer.msg('成功删除'+msg+'条数据', {icon: 1})
                    }else{
                        layer.msg('已删除或不存在!',{icon:1,time:1000});
                    }
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                }
            })
            ;
        });
    }
</script>
</body>

</html>
