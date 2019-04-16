    <%--
      Created by IntelliJ IDEA.
      User: Administrator
      Date: 2019/3/25 0025
      Time: 17:19
      To change this template use File | Settings | File Templates.
    --%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@include file="../background/commons/info.jsp" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>三味书屋</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
        </head>
        <body>
        <div id="wrap">

        <%--//动态引入文件--%>
        <jsp:include page="commons/header.jsp"/>


        <div class="center_content">
        <div class="left_content">
        <div class="title"><span class="title_icon"><img src="../images/bullet1.gif" alt="" title=""
        /></span>走进三味书屋...</div>

        <div class="feat_prod_box_details">

        <p class="details">
        &nbsp;&nbsp;&nbsp;未见面之前，我幻想过你无数中模样，我看见你在天寒地冻中被雾气缭绕，在末世洪荒里执着向前，在细碎光影里明媚如花。尚未靠近只因我们都在精心准备，我们在划破暗夜的启明星下，在繁声喧嚣的人潮汹涌中，执着等待岁月转角处、春暖花开里的牵手不离；末世流年里，枝影芳尘中，你一定和我一样，在期待每一次的温暖相遇...
        </p>

        <div class="contact_form">

        <div class="form_subtitle">注册一个新的账号</div>

        <form name="register" action="post" class="layui-form" >

        <div class="form_row">
        <%--<label class="contact"><strong>用户名:</strong></label>--%>
        <%--<input type="text" class="contact_input" />--%>
        <strong>用户名:</strong><input name="username" placeholder="用户名" type="text" lay-verify="username"
        class="layui-input" >
        </div>

        <div class="form_row">
        <%--<label class="contact"><strong>密码:</strong></label>--%>
        <%--<input type="text" class="contact_input" />--%>
        <strong>密码:</strong><input name="password" lay-verify="password" placeholder="密码" type="password"
        class="layui-input">
        </div>

        <div class="form_row">
        <%--<label class="contact"><strong>密码:</strong></label>--%>
        <%--<input type="text" class="contact_input" />--%>
        <strong>电子邮箱:</strong><input name="email" lay-verify="email" placeholder="邮箱" type="text" class="layui-input">
        </div>

        <div class="form_row">
        <%--<label class="contact"><strong>密码:</strong></label>--%>
        <%--<input type="text" class="contact_input" />--%>
        <strong>手机号:</strong><input name="phonenumber1" lay-verify="phone" placeholder="手机号" type="text" class="layui-input">
        </div>
        <div class="form_row">
        <%--<label class="contact"><strong>密码:</strong></label>--%>
        <%--<input type="text" class="contact_input" />--%>
        <strong>家庭住址:</strong><input name="site" lay-verify="site" placeholder="家庭住址" type="text" class="layui-input">
        </div>

        <%--<div class="form_row">--%>
        <%--<div class="terms">--%>
        <%--<input type="checkbox" name="terms" />--%>
        <%--记住我--%>
        <%--</div>--%>
        <%--</div>--%>

        <div class="form_row">
        <%--<input type="submit" class="register" value="登录" />--%>
        <input value="注册" lay-submit lay-filter="login" style="width:40%; height: 30px " type="submit" class="register">
        </div>

        </form>

        </div>
        </div>


        <script>

        $(function () {
        layui.use('form', function(index){
        var form = layui.form;
        form.verify({
        username: function(value, item){ //value：表单的值、item：表单的DOM对象
        if(!new RegExp("^[a-zA-Z][a-zA-Z0-9_]{4,15}$").test(value)){
        return '用户名字母开头，允许5-16字节，允许字母数字下划线';
        }
        }
        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        ,password: [
        /^[a-zA-Z]\w{5,17}$/,'密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线'
        ]
        });
        //监听提交
        form.on('submit(login)', function(data){
        $.ajax({
        type: "POST",
        url: "<%=request.getContextPath()%>/UserRegisterServlet",
        data: "username="+data.field.username+"&password="+data.field.password+"&email="+data.field.email+"&phonenumber1="+data.field.phonenumber1+"&site="+data.field.site,


        success: function(msg){
        var obj = eval("("+msg+")");
        if(obj.code=="1001"){
        layer.msg(obj.message,function(){
        location.href='register.jsp'
        })
        if(${username!=null}){
        // 获得frame索引
        var index = parent.layer.getFrameIndex(window.name);
        //关闭当前frame
        parent.layer.close(index);
        window.parent.location.reload();
        }
        }else{
        layer.msg(obj.message);
        }
        }
        });
        return false;
        });
        });
        })


        </script>


        <div class="clear"></div>
        </div><!--end of left content-->

        <div class="right_content">

        <%--商城寄语--%>
        <jsp:include page="commons/storemsg.jsp"/>
        <%--购物车--%>
        <jsp:include page="commons/shoppingcar.jsp"/>
        <%--商品促销--%>
        <%--<jsp:include page="promotions.jsp"/>--%>
        <div class="right_box">
        <div class="title"><span class="title_icon"><img src="<%=request.getContextPath()%>/images/bullet4.gif" alt=""
        title="" /></span>友情折扣</div>
        <c:forEach items="${threeSaleBook}" var="book">
            <div class="new_prod_box">
            <a href="<%=request.getContextPath()%>/BookServlet?action=queryOne&bookId=${book.bookId}">${book.bookName}</a>
            <div class="new_prod_bg">
            <a href="details.jsp"><img src="<%=request.getContextPath()%>/font/images/${book.imagepath}" width="60px" height="100px" alt="" title=""
            class="thumb" border="0" /></a>
            </div>
            </div>
        </c:forEach>
        </div>
        <%--分类--%>
        <%--<jsp:include page="categories.jsp"/>--%>
        <div class="right_box">
        <div class="title"><span class="title_icon"><img src="<%=request.getContextPath()%>/images/bullet5.gif" alt=""
        title="" /></span>分类</div>
        <ul class="list">
        <c:forEach items="${typeList}" var="booktype">
            <li><a href="<%=request.getContextPath()%>/BookServlet?action=queryByType&typeId=${booktype.typeId}">${booktype.typeName}</a></li>
        </c:forEach>
        </ul>

        <div class="title"><span class="title_icon"><img src="<%=request.getContextPath()%>/images/bullet6.gif" alt=""
        title="" /></span>友情链接</div>
        <ul class="list">
        <c:forEach items="${linkList}" var="link">
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
