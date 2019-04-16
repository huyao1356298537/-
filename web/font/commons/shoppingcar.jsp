<%--
  Created by IntelliJ IDEA.
  User: 张尧俊
  Date: 2019/3/25
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <div class="cart">
    <div class="title"><span class="title_icon"><img src="<%=request.getContextPath()%>/images/cart.gif" alt="" title="" /></span>我的购物车</div>
    <div class="home_cart_content">
    <%--3 x 商品 | <span class="red">总价: 100$</span>--%>
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
    </div>
    <a href="<%=request.getContextPath()%>/font/cart.jsp" class="view_cart" style="font-size: 15px">查看购物车</a>
    </div>

</body>
</html>
