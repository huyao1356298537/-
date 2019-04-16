<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/25 0025
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>三味书屋</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
</head>
<body>
<div id="wrap">

    <%--//动态引入文件--%>
    <jsp:include page="header.jsp"/>


    <div class="center_content">
        <div class="left_content">
            <div class="title"><span class="title_icon"><img src="../../images/bullet1.gif" alt="" title=""/></span>联系我们
            </div>

            <div class="feat_prod_box_details">
                <p class="details" style="font-size: 20px ;color:deepskyblue">
                    您的支持与陪伴是我们最大的期待...
                </p>

                <div class="contact_form">
                    <div class="form_subtitle">请填写以下所有项</div>
                    <div class="form_row">
                        <label class="contact"><strong>姓名:</strong></label>
                        <input type="text" class="contact_input"/>
                    </div>

                    <div class="form_row">
                        <label class="contact"><strong>邮箱:</strong></label>
                        <input type="text" class="contact_input"/>
                    </div>


                    <div class="form_row">
                        <label class="contact"><strong>电话:</strong></label>
                        <input type="text" class="contact_input"/>
                    </div>

                    <div class="form_row">
                        <label class="contact"><strong>公司:</strong></label>
                        <input type="text" class="contact_input"/>
                    </div>


                    <div class="form_row">
                        <label class="contact"><strong>留言:</strong></label>
                        <textarea class="contact_textarea"></textarea>
                    </div>


                    <div class="form_row">
                        <a href="#" class="contact">发送</a></div>
                </div>

            </div>


            <div class="clear"></div>
        </div><!--end of left content-->

        <div class="right_content">

            <%--引入商家寄语--%>
            <jsp:include page="../commons/storemsg.jsp"/>
            <%--引入购物车--%>
            <jsp:include page="../commons/shoppingcar.jsp"/>
            <%--引入打折书籍--%>
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
    <jsp:include page="footer.jsp"/>

</div>

</body>
</html>