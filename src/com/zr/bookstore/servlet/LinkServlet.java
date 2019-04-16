package com.zr.bookstore.servlet;

import com.zr.bookstore.entity.Link;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.service.LinkService;
import com.zr.bookstore.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LinkServlet",urlPatterns = "/LinkServlet",initParams = {@WebInitParam(name ="pageCount" ,value = "5")} )
public class LinkServlet extends HttpServlet {
    private LinkService service=new LinkService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        if("query".equals(action)){
            query(request,response);
        }else if("add".equals(action)){
            add(request,response);
        }else if("update".equals(action)){
            update(request,response);
        }else if("delete".equals(action)){
            delete(request,response);
        }else if("deleteAll".equals(action)){
            deleteAll(request,response);
        }else if("queryOne".equals(action)){
            queryOne(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("kid");
        int linkId=-1;
        if(!StringUtil.isEmpty(id)){
            linkId=Integer.parseInt(id);
        }
        Link link = service.queryOne(linkId);
        request.setAttribute("link",link);
        request.getRequestDispatcher("/background/link/linkupdate.jsp").forward(request,response);
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String linkId = request.getParameter("linkId");
        String linkName = request.getParameter("linkName");
        String email = request.getParameter("email");
        String linkUrl = request.getParameter("linkUrl");
        String linkOrder = request.getParameter("linkOrder");

        Link link=new Link(Integer.parseInt(linkId),linkName,email,linkUrl,Integer.parseInt(linkOrder));
        int i = service.update(link);
        response.getWriter().print(""+i);
    }
    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] id = ids.split(",");
        int sum=0;
        for (String linkId: id) {
            int i = service.delete(Integer.parseInt(linkId));
            sum+=i;
        }
        response.getWriter().print(""+sum);

    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("kid");
        int linkId=-1;
        if(!StringUtil.isEmpty(id)){
            linkId=Integer.parseInt(id);
        }
        int i = service.delete(linkId);
        response.getWriter().print(""+i);

    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String linkName = request.getParameter("linkName");
        String email = request.getParameter("email");
        String linkUrl = request.getParameter("linkUrl");
        String linkOrder = request.getParameter("linkOrder");

        Link link=new Link(linkName,email,linkUrl,Integer.parseInt(linkOrder));
        int i = service.addLink(link);
        response.getWriter().print(""+i);
        System.out.println(i);

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
        List<Link> linkList = service.queryLinkPage(pageBean);
        request.setAttribute("linkList",linkList);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("background/link/linklist.jsp").forward(request,response);
    }

}
