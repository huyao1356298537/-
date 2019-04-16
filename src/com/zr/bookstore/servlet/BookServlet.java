package com.zr.bookstore.servlet;

import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.service.BookService;
import com.zr.bookstore.utils.DateUtil;
import com.zr.bookstore.utils.StringUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = "/BookServlet",initParams = {@WebInitParam(name ="pageCount" ,value = "5")})
public class BookServlet extends HttpServlet {
         private   BookService service = new BookService();

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");
        if ("query".equals(action)){
            query(request,response);
        } else if ("queryOne".equals(action)){
            queryOne(request,response);
        }else if ("add".equals(action)){
            add(request,response);
        }else if ("delete".equals(action)){
            delete(request,response);
        }else if ("update".equals(action)){
            update(request,response);
        }else if ("deleteAll".equals(action)){
            deleteAll(request,response);
        }else if ("queryOneBook".equals(action)){
            queryOneBook(request,response);
        }else if ("queryByType".equals(action)){
            queryByType(request,response);
        }

    }

    private void queryByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String typeId = request.getParameter("typeId");
        int i = Integer.parseInt(typeId);
        List<Book> bookList = service.findBookByType(i);
        session.setAttribute("bookList",bookList);
        request.getRequestDispatcher("/font/typebook.jsp").forward(request,response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageBean pageBean=new PageBean();
        //获取页码
        String pageIndex = request.getParameter("pageIndex");
        if(!StringUtil.isEmpty(pageIndex)){
            pageBean.setPageIndex(Integer.parseInt(pageIndex));
        }
        //获取每页条数
        String pageCount = getInitParameter("pageCount");
        pageBean.setPageCount(Integer.parseInt(pageCount));
        //获取总条数
        pageBean.setCount(service.getCount());

        //获取数据
        List<Book> bookList = service.queryBookPage(pageBean);
        System.out.println(bookList);
        request.setAttribute("bookList",bookList);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("background/books/booklist.jsp").forward(request,response);
    }

    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得的bookId使字符串类型的
        String bookId = request.getParameter("bookId");
        System.out.println(bookId);
        //获得session对象
        HttpSession session = request.getSession();
        int bId = -1 ;
        if (!StringUtil.isEmpty(bookId)){
            bId = Integer.parseInt(bookId);
        }
        Book book = service.findBookById(bId);
        System.out.println(book.getBookName());
        session.setAttribute("book",book);

        request.getRequestDispatcher("/font/details.jsp").forward(request,response);
    }


    private void queryOneBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        //获得的bookId使字符串类型的
        String bookId = request.getParameter("kid");
        //获得session对象
        HttpSession session = request.getSession();
        int bId = -1 ;
        if (!StringUtil.isEmpty(bookId)){
            bId = Integer.parseInt(bookId);
        }
        Book book = service.findBookById(bId);
        request.setAttribute("book",book);
        request.getRequestDispatcher("background/books/bookupdate.jsp").forward(request,response);
    }


    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String sellcount = request.getParameter("sellcount");
        String repertory = request.getParameter("repertory");
        String typeId = request.getParameter("typeId");
        String imagepath = request.getParameter("imagepath");
        String publicationDate = request.getParameter("publicationDate");
        String issale = request.getParameter("issale");
        String press = request.getParameter("press");
        Book book = new Book(bookName,author, Integer.parseInt(price), Integer.parseInt(sellcount), Integer.parseInt(repertory),
                Integer.parseInt(typeId), imagepath, DateUtil.formatString(publicationDate,"yyyyMMdd"), Integer.parseInt(issale),press);
        int i = service.add(book);
        response.getWriter().print(""+i);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("kid");
        int bookId=-1;
        if(!StringUtil.isEmpty(id)){
            bookId=Integer.parseInt(id);
        }
        int i = service.delete(bookId);
        response.getWriter().print(""+i);
    }
    //批量删除
    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] id = ids.split(",");
        int sum=0;
        for (String bookId: id) {
            int i = service.delete(Integer.parseInt(bookId));
            sum+=i;
        }
        response.getWriter().print(""+sum);
    }
    //修改
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookId = request.getParameter("bookId");
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String sellcount = request.getParameter("sellcount");
        String repertory = request.getParameter("repertory");
        String typeId = request.getParameter("typeId");
        String imagepath = request.getParameter("imagepath");
        String publicationDate = request.getParameter("publicationDate");
        String issale = request.getParameter("issale");
        String press = request.getParameter("press");

        Book book = new Book(Integer.parseInt(bookId),bookName,author, Integer.parseInt(price), Integer.parseInt(sellcount), Integer.parseInt(repertory),
                Integer.parseInt(typeId), imagepath, DateUtil.formatString(publicationDate,"yyyyMMdd"), Integer.parseInt(issale),press);
        int i = service.update(book);
        response.getWriter().print(i);
    }
}
