package com.zr.bookstore.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zr.bookstore.dao.BookDao;
import com.zr.bookstore.dao.BookTypeDao;
import com.zr.bookstore.dao.daoimpl.BookDaoImpl;
import com.zr.bookstore.dao.daoimpl.BookTypeDaoImpl;
import com.zr.bookstore.entity.BookType;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.service.BookTypeService;
import com.zr.bookstore.service.ResultCode;
import com.zr.bookstore.utils.JsonUtils;
import com.zr.bookstore.utils.StringUtil;

@WebServlet(name = "BookTypeServlet" ,urlPatterns = "/BookTypeServlet")
public class BookTypeServlet extends HttpServlet {
    BookTypeService service = new BookTypeService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");
        if ("query".equals(action)){
            query(request,response);
        }else if ("queryOne".equals(action)){
            queryOne(request,response);
        }else if ("add".equals(action)){
            add(request,response);
        }else  if ("delete".equals(action)){
            delete(request,response);
        }else if ("update".equals(action)){
            update(request,response);
        }else if ("deleteAll".equals(action)){
            deleteAll(request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("进来了");

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        System.out.println(page);


        PageBean pageBean = new PageBean();
        pageBean.setPageIndex(Integer.parseInt(page));
        pageBean.setPageCount(Integer.parseInt(limit));
        pageBean.setCount(service.findAll().size());

        List<BookType> typeList =service.queryBookTypePage(pageBean);
        JSONObject array = JsonUtils.getJsonObject(typeList,pageBean);
        response.getWriter().print(array);

    }

    //    查询一个
    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int typeId=-1;
        if(!StringUtil.isEmpty(id)){
            typeId=Integer.parseInt(id);
        }
        BookType type = service.findTypeById(typeId);
        request.setAttribute("type",type);
        request.getRequestDispatcher("/background/booktype/typeadd.jsp").forward(request,response);
    }
    //  添加
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeName = request.getParameter("typeName");
        BookTypeDao dao = new BookTypeDaoImpl();
        int i = dao.add(new BookType(typeName));
        response.getWriter().print(i);
    }
    //    删除
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeId = request.getParameter("typeId");
        int id = Integer.parseInt(typeId);
        BookDao bookDao = new BookDaoImpl();
        int bookCount = bookDao.findBookCountByType(id);
        ResultCode resultCode = new ResultCode();

        if (bookCount==0){
            BookTypeDao dao = new BookTypeDaoImpl();
            int i = dao.delete(id);
            if (i>0){
                resultCode.setCode("2001");
                resultCode.setMessage("图书类型删除成功");
            }else{
                resultCode.setCode("2002");
                resultCode.setMessage("图书类型已删除或不存在");
            }
        }else{
            resultCode.setCode("2003");
            resultCode.setMessage("图书类型下有图书不可删除");
        }
        String json = JSONObject.toJSONString(resultCode);
        System.out.println(json);
        response.getWriter().print(json);

    }
    //批量删除
    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookTypeDao dao = new BookTypeDaoImpl();
        String ids = request.getParameter("ids");
        String[]id = ids.split(",");
        int sum = 0;
        for (String typeId:id){
            int i =dao.delete(Integer.parseInt(typeId));
            sum+=i;
        }
        response.getWriter().print(""+sum);
    }
    //    修改
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeId = request.getParameter("typeId");
        String typeName = request.getParameter("typeName");

        BookType bookType = new BookType(Integer.parseInt(typeId), typeName);
        BookTypeService bookTypeService = new BookTypeService();

        int i = bookTypeService.update(bookType);
        response.getWriter().print(i);
    }

}
