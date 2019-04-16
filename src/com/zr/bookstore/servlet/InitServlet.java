package com.zr.bookstore.servlet;
import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.BookType;
import com.zr.bookstore.entity.Member;
import com.zr.bookstore.service.BookService;
import com.zr.bookstore.service.BookTypeService;
import com.zr.bookstore.service.MemberService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InitServlet",urlPatterns = "/InitServlet",loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    private BookTypeService typeService = new BookTypeService();
    private BookService bookService = new BookService();
    private MemberService memberService = new MemberService();

    @Override
    public void init() throws ServletException {

        ServletContext application = this.getServletContext();

        // 图书加载
        List<Book> bookList = bookService.findAll();
        application.setAttribute("bookList",bookList);

        // 图书类别加载
        List<BookType> typeList = typeService.findAll();
        application.setAttribute("typeList",typeList);

        // 会员总数
        List<Member> memberList = memberService.findAll();
        application.setAttribute("memberList",memberList);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ServletContext application = this.getServletContext();

        // 图书加载
        List<Book> bookList = bookService.findAll();
        application.setAttribute("bookList",bookList);

        // 图书类别加载
        List<BookType> typeList = typeService.findAll();
        application.setAttribute("typeList",typeList);

        // 会员总数
        List<Member> memberList = memberService.findAll();
        application.setAttribute("memberList",memberList);
    }

    private void LoginInfo(HttpServletRequest request, HttpServletResponse response) {
        //创建一次会话，如果有会话就直接使用
        HttpSession session =request.getSession();
        String username = (String)session.getAttribute("username");

        //获取总数
        List<Book> bookList = bookService.findAll();
        List<BookType> typeList = typeService.findAll();
        List<Member> memberList = memberService.findAll();

        session.setAttribute("username",username);
        session.setAttribute("bookListCount",bookList.size());
        session.setAttribute("typeListCount",typeList.size());
        session.setAttribute("memberListCount",memberList.size());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
