    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%--
          Created by IntelliJ IDEA.
          User: Administrator
          Date: 2019/3/25 0025
          Time: 17:17
          To change this template use File | Settings | File Templates.
        --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
    <title>三味书屋</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />

    </head>
    <body>
    <div id="wrap">

    <%--//动态引入文件--%>
    <jsp:include page="commons/header.jsp" />


    <div class="center_content">
    <div class="left_content">
    <div class="title"><span class="title_icon"><img src="../images/bullet1.gif" alt="" title="" /></span>热销书籍</div>

    <div class="new_products">


    <c:forEach items="${hotBook}"  var="book" >
        <div class="new_prod_box">
        <a href="<%=request.getContextPath()%>/BookServlet?action=queryOne&bookId=${book.bookId}">${book.bookName}</a>
        <div class="new_prod_bg">
        <a href="<%=request.getContextPath()%>/BookServlet?action=queryOne&bookId=${book.bookId}"><img src="<%=request.getContextPath()%>/font/images/${book.imagepath}" width="60px" height="100px" alt="" title="" class="thumb" border="0" /></a>
        </div>
        </div>
    </c:forEach>

    </div>


    <div class="clear"></div>
    </div><!--end of left content-->

    <div class="right_content">
    <%--<div class="languages_box">--%>
    <%--<span class="red">Languages:</span>--%>
    <%--<a href="#"><img src="../images/gb.gif" alt="" title="" border="0" /></a>--%>
    <%--<a href="#"><img src="../images/fr.gif" alt="" title="" border="0" /></a>--%>
    <%--<a href="#"><img src="../images/de.gif" alt="" title="" border="0" /></a>--%>
    <%--</div>--%>
    <%--<div class="currency">--%>
    <%--<span class="red">Currency: </span>--%>
    <%--<a href="#">GBP</a>--%>
    <%--<a href="#">EUR</a>--%>
    <%--<a href="#"><strong>USD</strong></a>--%>
    <%--</div>--%>

    <%--商城寄语--%>
    <jsp:include page="commons/storemsg.jsp"/>
    <%--购物车--%>
    <jsp:include page="commons/shoppingcar.jsp"/>
    <%--引入促销--%>
    <%--<jsp:include page="commons/promotions.jsp"/>--%>
        <div class="right_box">
        <div class="title"><span class="title_icon"><img src="<%=request.getContextPath()%>/images/bullet4.gif" alt="" title="" /></span>友情折扣</div>
        <c:forEach items="${threeSaleBook}"  var="book" >
            <div class="new_prod_box">
            <a href="<%=request.getContextPath()%>/BookServlet?action=queryOne&bookId=${book.bookId}">${book.bookName}</a>
            <div class="new_prod_bg">
            <a href="<%=request.getContextPath()%>/BookServlet?action=queryOne&bookId=${book.bookId}"><img src="<%=request.getContextPath()%>/font/images/${book.imagepath}" width="60px" height="100px" alt="" title="" class="thumb" border="0" /></a>
            </div>
            </div>
        </c:forEach>
        </div>
    <%--引入分类及合作伙伴--%>
    <%--<jsp:include page="commons/categories.jsp"/>--%>
        <div class="right_box">
        <div class="title"><span class="title_icon"><img src="<%=request.getContextPath()%>/images/bullet5.gif" alt="" title="" /></span>分类</div>
        <ul class="list">
        <c:forEach items="${typelist}" var="booktype" >
            <li><a href="<%=request.getContextPath()%>/BookServlet?action=queryByType&typeId=${booktype.typeId}">${booktype.typeName}</a></li>
        </c:forEach>

        </ul>
        <div class="title"><span class="title_icon"><img src="<%=request.getContextPath()%>/images/bullet6.gif" alt="" title="" /></span>友情链接</div>
        <ul class="list">
        <c:forEach var="link" items="${linkList}">
            <li>
            <a href="${link.linkUrl}" target="_blank">${link.linkName}</a>
            </li>
        </c:forEach>

        </ul>
        </div>


        </div>







    <div class="clear"></div>
    </div><!--end of center content-->


    <%--引入底部模块--%>
    <jsp:include page="commons/footer.jsp"/>

    </div>

    </body>
    </html>
