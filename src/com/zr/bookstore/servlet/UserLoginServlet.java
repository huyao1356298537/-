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


/**
 * @author : huyao
 * date : 2019/3/19
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username==="+username);
        MemberService memberService = new MemberService();
        Member member = memberService.queryOneByName(username);
        System.out.println("member==="+member);
        ResultCode rc = null;
        if(member!=null){
            //用户名正确
            //  rc = new ResultCode("1001","用户名正确");
            if(member.getPassword().equals(password)){
                //密码正确
                rc = new ResultCode("1001","登录成功");
                request.getSession().setAttribute("currentDate", DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
                loginInfo(request,response);
            }else{
                //密码不正确
                rc = new ResultCode("1002","密码不正确");
            }
        }else{
            //用户名不存在
            rc = new ResultCode("1000","你是不是不知道账号密码！？？？");
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


