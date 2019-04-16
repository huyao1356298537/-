package com.zr.bookstore.servlet;

import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.BookType;
import com.zr.bookstore.entity.Link;
import com.zr.bookstore.service.BookService;
import com.zr.bookstore.service.BookTypeService;
import com.zr.bookstore.service.LinkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet" ,urlPatterns = "/MainServlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        //实例化Service对象
        BookTypeService bookTypeService = new BookTypeService();
        BookService bookService = new BookService();
        LinkService linkService = new LinkService();

        //获取所有图书类型
        List<BookType> typelist = bookTypeService.findAll();
//      request.setAttribute("typelist",typelist);
        session.setAttribute("typelist",typelist);
        //获取所有的链接
        List<Link> linkList = linkService.findAll();
        //     request.setAttribute("linkList",linkList);
        session.setAttribute("linkList",linkList);
        //获取新书
        List<Book> newBookList = bookService.findNewBook();
//        request.setAttribute("newBook",newBookList);
        session.setAttribute("newBook",newBookList);

        //获取热门书籍
        List<Book> hotBookList = bookService.findHotBook();
//        request.setAttribute("hotBook",hotBookList);
        session.setAttribute("hotBook",hotBookList);


        //获取打折书籍
        List<Book> saleBookList = bookService.findSaleBook();
//        request.setAttribute("saleBook",saleBookList);
        session.setAttribute("saleBook",saleBookList);

        //获取3本折扣书
        List<Book> threeSaleBookList = bookService.findThreeSaleBook();
//      request.setAttribute("threeSaleBook",threeSaleBookList);
        session.setAttribute("threeSaleBook",threeSaleBookList);



        request.getRequestDispatcher("/font/fontmain.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
