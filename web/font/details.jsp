    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@include file="../background/commons/info.jsp"%>
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>三味书屋</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lightbox.css" type="text/css" media="screen" />

        <script src="../js/prototype.js" type="text/javascript"></script>
        <script src="../js/scriptaculous.js?load=effects" type="text/javascript"></script>
        <script src="../js/lightbox.js" type="text/javascript"></script>
        <script type="text/javascript" src="../js/java.js"></script>
        </head>
        <body>
        <div id="wrap">

        <%--导航栏--%>
        <%--动态引入文件--%>
        <jsp:include page="commons/header.jsp" />

        <div class="center_content">
        <div class="left_content">
        <div class="title"><span class="title_icon"><img src="../images/bullet1.gif" alt="" title="" /></span>图书详情</div>

        <div class="feat_prod_box_details">

        <div class="prod_img"><a href="#"><img src="<%=request.getContextPath()%>/font/images/${book.imagepath}" width="140px" height="140px" alt="" title="" border="0" /></a>
        <br /><br />
        <a href="<%=request.getContextPath()%>/font/images/${book.imagepath}" rel="lightbox"><img src="<%=request.getContextPath()%>/images/zoom.gif" alt="" title="" border="0" /></a>
        </div>

        <div class="prod_det_box">
        <div class="box_top"></div>
        <div class="box_center">
        <div class="prod_title" style="font-size:22px;" align="center">${book.bookName}</div>
        <p class="details">
        <div class="price" ><strong>编号:</strong><span id="idb">${book.bookId}</span></div>
        <div class="price"><strong>作者:</strong> ${book.author}</div>
        <div class="price"><strong>出版社:</strong>${book.press}</div>
        <div class="price"><strong>出版时间:</strong>${book.publicationDate}</div>
        <div class="price"><strong>销量:</strong> ${book.sellcount}</div>
        <div class="price"><strong>库存:</strong>${book.repertory}</div>
        <div class="price"><strong>价格:</strong> <span class="red">￥${book.price}</span></div>

        </p>

        <%--这是添加到购物车的按钮--%>
        <%--<a href="<%=request.getContextPath()%>/CartServlet?action=add&bookId="+${book.bookId} class="more"><img src="../images/order_now.gif"--%>
        <%--alt="" title="" border="0" /></a>--%>
        <button  onclick="toCartServlet()">加入购物车</button>
        <script>
        function toCartServlet() {
        var id = document.getElementById("idb").innerHTML
        alert(id)
        location.href="<%=request.getContextPath()%>/CartServlet?action=add&id="+id
        }
        </script>

            <%--<input class="addCartBtn" type="button" value="加入购物车" >--%>
            <div class="clear"></div>
            </div>



            <div class="box_bottom"></div>
            </div>
            <div class="clear"></div>
            </div>

            <div id="official-remind" class="j-mdv" style="  width: 90% ;margin-top: 80px; padding: 10px; background-color: #ffffe5; border: 1px solid #ffcc7f;">
            <dl class="tb-secu" data-spm-anchor-id="a220o.1000855.0.i3.a0e4129fYsuH4V" style=" margin-bottom: 10px;padding-bottom: 10px;border-bottom: 1px solid #ffe8ca; ">
            <dt style="display: inline;font-weight: 700;  ">安全提示：</dt>
            <dd>
            <p style="line-height: 2; " >请勿随意接收任何来源不明的文件，请勿随意点击任何来源不明的链接。
            涉及资金往来的事项请务必仔细核对资金往来信息。
            三味书屋不会以订单有问题，让您提供任何银行卡、密码、手机验证码！
            遇到可疑情况可在钱盾“诈骗举报”中进行举报!
            </p>
            <p class="tm-secu clearfix" data-spm-anchor-id="a220o.1000855.0.i1.a0e4129fYsuH4V">
            <a>推荐安全软件：</a>
            <a href="http://qd.alibaba.com/?tracelog=detail" target="_blank"><span class="tm-secu-qd" style=" margin: 4px 10px 0 0;color: #999;padding-left: 18px;height: 16px;line-height: 16px;" >钱盾</span></a>
            <a href="http://www.google.cn/" target="_blank"><span class="tm-secu-uc" style=" margin: 4px 10px 0 0;color: #999;padding-left: 18px;height: 16px;line-height: 16px;">谷歌浏览器</span></a>
            </p>
            </dd>
            </dl>
            <dl class="tb-exemption" style=" line-height: 2;">
            <dt style=" display: inline; font-weight: 700;">内容声明：</dt>
            <dd data-spm-anchor-id="a220o.1000855.0.i0.a0e4129fYsuH4V">
            三味书屋为第三方交易平台及互联网信息服务提供者，
            三味书屋所展示的商品/服务的标题、价格、详情等信息内容系由店铺经营者发布，
            其真实性、准确性和合法性均由店铺经营者负责。三味书屋提醒用户购买商品/服务前注意谨慎核实。
            如用户对商品/服务的标题、价格、详情等任何信息有任何疑问的，请在购买前通过微信与店铺经营者沟通确认；
            三味书屋存在海量店铺，如用户发现店铺内有任何违法/侵权信息，请立即向三味书屋举报并提供有效线索。
            感谢您的配合使用!
            </dd>
            </dl>
            </div>



        <div class="clear"></div>
        </div><!--end of left content-->

        <div class="right_content">

        <%--引入商家寄语--%>
        <jsp:include page="commons/storemsg.jsp"/>
        <%--引入购物车--%>
        <jsp:include page="commons/shoppingcar.jsp"/>
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
        </div>
        <!--end of center content-->



        <%--引入底部模块--%>
        <jsp:include page="commons/footer.jsp"/>

        </div>

        </body>
        <script type="text/javascript">
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

        var tabber1 = new Yetii({
        id: 'demo'
        });

        </script>
        </html>



