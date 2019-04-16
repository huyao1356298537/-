<%--
  Created by IntelliJ IDEA.
  User: 张尧俊
  Date: 2019/3/25
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div class="right_box">
<div class="title"><span class="title_icon"><img src="<%=request.getContextPath()%>/images/bullet4.gif" alt="" title="" /></span>友情折扣</div>
    <c:forEach items="${threeSaleBook}"  var="book" >
        <div class="new_prod_box">
            <a href="<%=request.getContextPath()%>/BookServlet?action=queryOne&bookId=${book.bookId}">${book.bookName}</a>
            <div class="new_prod_bg">
                <a href="details.jsp"><img src="<%=request.getContextPath()%>/font/images/${book.imagepath}" width="60px" height="100px" alt="" title="" class="thumb" border="0" /></a>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
