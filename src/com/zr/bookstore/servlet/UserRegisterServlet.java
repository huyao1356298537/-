package com.zr.bookstore.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zr.bookstore.dao.LinkDao;
import com.zr.bookstore.dao.daoimpl.*;
import com.zr.bookstore.entity.*;
import com.zr.bookstore.service.MemberService;
import com.zr.bookstore.service.ResultCode;
import com.zr.bookstore.utils.DateUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;



@WebServlet(value = "/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phonenumber = request.getParameter("phonenumber1");
        String site = request.getParameter("site");

        System.out.println(phonenumber);
        System.out.println(site);
        Member member = new Member(username, password, email, phonenumber, site);
        MemberService memberService = new MemberService();
        int i = memberService.addMember(member);
//        System.out.println(i);
        ResultCode rc = null;
            //用户名正确
            //  rc = new ResultCode("1001","用户名正确");
            if(i==1){
                //密码正确
                rc = new ResultCode("1001","注册成功");
                request.getSession().setAttribute("currentDate", DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
                loginInfo(request,response);
            }else{
                //密码不正确
                rc = new ResultCode("1002","注册失败");
            }
        response.getWriter().print(JSONObject.toJSONString(rc));
    }

    private void loginInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建一次会话， 如果有会话就直接使用
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        LinkDao linkDao=new LinkDaoImpl();
        List<Link> linkList = linkDao.findAll();

        session.setAttribute("username",username);
        session.setAttribute("linkListCount",linkList.size());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}


