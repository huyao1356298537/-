    <%--
      Created by IntelliJ IDEA.
      User: Administrator
      Date: 2019/3/25 0025
      Time: 17:43
      To change this template use File | Settings | File Templates.
    --%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
        <title>三味书屋</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
            <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>

        </head>
        <body>
        <div id="wrap">

        //动态引入文件
        <jsp:include page="commons/header.jsp"/>


        <div class="center_content">

        <div class="left_content">
        <div class="title"><span class="title_icon"><img src="../images/bullet1.gif" alt="" title=""
        /></span>我的购物车</div>

        <div class="feat_prod_box_details">

        <table class="cart_table">
        <tr class="cart_title">
        <td>书名</td>
        <td>作者</td>
        <td>价格</td>
        <td>数目</td>
        <td>总价</td>
        </tr>

        <c:forEach items="${cartList}" var="cart">
            <tr>
            <td id="nameid">${cart.bookName}</td>
            <td >${cart.author}</td>
            <td>${cart.price}</td>
            <td>${cart.count}</td>
            <td>${cart.price}</td>
            </tr>
        </c:forEach>

        <tr>
        <td colspan="4" class="cart_total"><span class="red">总计:</span></td>
         <td id="amount">${sum}</td>
        </tr>

        </table>
        <%--<a href="/MainServlet" class="continue">&lt; 继续购买</a>--%>
        <%--<a href="#" class="checkout">立即结算 &gt;</a>--%>
        <button class="checkout" onclick="toMainServlet()">继续购买</button>
        <button class="checkout" onclick="toOrderServlet()">立即结算</button>
        <script>
            function toMainServlet() {
                location.href="<%=request.getContextPath()%>/MainServlet"
            }
                function toOrderServlet() {
                var name = prompt("请输入收货人姓名")
                var phoneNumber = prompt("请输入收货人手机号")
                var amount = document.getElementById("amount").innerHTML
                var bookName = document.getElementById("nameid").innerHTML
            //AJAX
        /*    $.ajax({
                  type:"post",
                  url:"/OrderServlet",
                data:{
                      "action":"add",
                      "oid":id,
                      "name":name,
                      "phoneNumber":phoneNumber
            },
            success:function(msg) {
                if (msg==1) {
                    alert(123)
            }
            }
            })*/
                location.href="<%=request.getContextPath()%>/OrderServlet?action=add&amount="+amount+"&name="+name+"&phoneNumber="+phoneNumber+"&bookName="+bookName
                }
        </script>
        </div>
        <div class="clear"></div>
        </div><!--end of left content-->


        <div class="right_content">

        <%--&lt;%&ndash;引入购物车&ndash;%&gt;--%>
        <%--<jsp:include page="commons/shoppingcar.jsp"/>--%>
        <%--引入商家寄语--%>
        <jsp:include page="commons/storemsg.jsp"/>
        <%--商品促销--%>
        <%--<jsp:include page="promotions.jsp"/>--%>
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
        <%--分类--%>
        <%--<jsp:include page="categories.jsp"/>--%>
        <div class="right_box">
        <div class="title"><span class="title_icon"><img src="<%=request.getContextPath()%>/images/bullet5.gif" alt="" title="" /></span>分类</div>
        <ul class="list">
        <c:forEach items="${typeList}" var="booktype" >
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


        <%--引入底部模块--%>
        <jsp:include page="commons/footer.jsp"/>


        </div>

        </body>
        </html>