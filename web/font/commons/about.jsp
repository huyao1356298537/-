<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/25 0025
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>三味书屋</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
    </head>
    <body>
    <div id="wrap">

    <%--//动态引入文件--%>
    <jsp:include page="header.jsp" />


    <div class="center_content">
    <div class="left_content">
    <div class="title"><span class="title_icon"><img src="../../images/bullet1.gif" alt="" title="" /></span>关于我们</div>

    <div class="feat_prod_box_details">
    <p class="details" style=" font-family:宋体 ;color: #990000;">
        <img src="../../images/about.gif" alt="" title="" class="right" style="width: 200px;height: 200px" />

        &nbsp; &nbsp;走过万水千山，唯有读书最为高贵。那就去读书吧！悄悄地告诉你，有个读书的地方最滋养灵魂——三味书屋。所有人在物质满足了之后，精神层面必然会寻找一种慰籍，这样才会觉得幸福。幸福的人总觉得自己最高贵，高贵的人只有读书才可以彰显其色彩。三味书屋是一个可以滋养灵魂的地方。它打造一个城市的文化之眼，它打造一个文明小区最优雅的链接，它是走进你我灵魂深处，伴着高贵让我们更高贵的源泉，它是温暖这座城市的一家书店。<br>

    　　仲夏时光，悠闲地走在夜的凉爽中，一束温和的光打在了一块陈旧的牌匾上，“三味书屋”这四个很温馨的字与我们的心灵契合。于是，走进书城，静静地坐在那里，心无旁骛地在书中度过一段清浅时光。淡然吟出：莫负时光 ，莫忘书香。<br>

    　　坐在三味书屋，看着书，品着咖啡或者茶，书香伴着茶香、咖啡香，还有花香，一缕缕随着时间与阳光，扩散到每一个角落，滋润到每一位读者的心里。温暖到了，真的温暖到了。三味书屋现在有书法学习，插画园艺，朗诵文学交流平台，烹茗品茶论道聊天花屋。在这里大家一起度过美好的时光，不断会遇到人生中素昧平生却又志趣相投的人们。他们用自已的双手，传递给这座城市阅读和园艺所带来的精神力量，感动着来书店的每一位朋友。我若不知道自已要去哪里，不由自主地就走到了三味书屋，推开书店的大门，心中的烦躁一下就不知所踪。到三味书屋静静守候一段时光，人生与书相随，便是流光溢彩。蓦然所思，若有诗书藏在心，岁月从不败美人。一切一切的情怀，都在书香、茶香、花香中流泻释怀...
    </p>
    </div>
    </div>

    <%--<div class="clear"></div>--%>
    <!--end of left content-->
    <div class="right_content">

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
