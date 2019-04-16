package com.zr.bookstore.servlet;

import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.service.BookService;
import com.zr.bookstore.utils.StringUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "RepertoryServlet",urlPatterns = "/RepertoryServlet",initParams = {@WebInitParam(name ="pageCount" ,value = "5")})
public class RepertoryServlet extends HttpServlet {

    private BookService service = new BookService();
//    private BookTypeService typeService =  new BookTypeService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");
        if ("query".equals(action)){
            query(request,response);
        } else if ("queryOne".equals(action)){
            queryOne(request,response);
        } else if ("delete".equals(action)){
            delete(request,response);
        }else if ("update".equals(action)){
            update(request,response);
        }else if ("deleteAll".equals(action)) {
            deleteAll(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
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

        //通过类型Id查询到类型名称
//        typeService.queryOne(bookList.)
        
        request.setAttribute("bookList",bookList);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("background/repertory/repertorylist.jsp").forward(request,response);
/////////////


//        String page = request.getParameter("page");
//        String limit = request.getParameter("limit");
//
//        System.out.println(page);
//        System.out.println(limit);
//
//        PageBean pageBean = new PageBean();
//        pageBean.setPageIndex(Integer.parseInt(page));
//        pageBean.setPageCount(Integer.parseInt(limit));
//        pageBean.setCount(service.findAll().size());
//
//        List<Book> bookList =service.queryBookPage(pageBean);
//        JSONObject array = JsonUtils.getJsonObject(bookList,pageBean);
//        response.getWriter().print(array);
    }

    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我进来了===queryOne");
        String bookId = request.getParameter("bookId");
        int mId = -1;
        if (!StringUtil.isEmpty(bookId)){
            mId= Integer.parseInt(bookId);
        }
        Book book = service.findBookById(mId);
        System.out.println("queryOne--book===="+book);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/background/repertory/repertoryupdate.jsp").forward(request,response);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kid = request.getParameter("kid");
        int mId = -1;
        if (!StringUtil.isEmpty(kid)){
            mId = Integer.parseInt(kid);
        }
        int i = service.delete(mId);
        response.getWriter().print(""+i);
    }

    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] id = ids.split(",");
        int sum = 0 ;
        for (String mId:id) {
            int i = service.delete(Integer.parseInt(mId));
            System.out.println(i);
            sum+=i;
        }
        response.getWriter().print(""+sum);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("我进来了===update");
        String bookId = request.getParameter("bookId");
        String bookName = request.getParameter("bookName");
        String typeId = request.getParameter("typeId");
        String repertory = request.getParameter("repertory");

        BookService bookService = new BookService();
        Book bookById = bookService.findBookById(Integer.parseInt(bookId));
        Date publicationDate = bookById.getPublicationDate();
        Book book = new Book(Integer.parseInt(bookId),bookName,bookById.getAuthor(),bookById.getPrice(),bookById.getSellcount(), Integer.parseInt(repertory),
               Integer.parseInt(typeId),bookById.getImagepath(),publicationDate, bookById.getissale(),bookById.getPress() );
        int i = service.update( book);
        response.getWriter().print(i);
    }

}
