package com.zr.bookstore.servlet;
import com.zr.bookstore.dao.MemberDao;
import com.zr.bookstore.dao.daoimpl.MemberDaoImpl;
import com.zr.bookstore.entity.Member;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.service.MemberService;
import com.zr.bookstore.utils.StringUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MemberServlet",urlPatterns = "/MemberServlet",initParams = {@WebInitParam(name="pageCount",value = "5")})
public class MemberServlet extends HttpServlet {
    private MemberDao dao = new MemberDaoImpl();
    private MemberService service = new MemberService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");

        if ("query".equals(action)){
            query(request,response);
        }else if ("add".equals(action)){
            add(request,response);
        }else if ("update".equals(action)){
            update(request,response);
        }else if ("delete".equals(action)){
            delete(request,response);
        }else if ("deleteAll".equals(action)){
            deleteAll(request,response);
        }else if ("queryOne".equals(action)){
            queryOne(request,response);
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mId = request.getParameter("mId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phonenumber = request.getParameter("phonenumber");
        String site = request.getParameter("site");
        System.out.println(mId);
        Member member = new Member(Integer.parseInt(mId),username,password,email,phonenumber,site);
        int i = service.updateMember(member);
        response.getWriter().print(i);
    }
    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kid = request.getParameter("kid");
        int mId = -1;
        if (!StringUtil.isEmpty(kid)){
            mId= Integer.parseInt(kid);
        }
        Member member = service.queryOne(mId);
        request.setAttribute("member",member);
        request.getRequestDispatcher("/background/member/memberupdate.jsp").forward(request,response);
    }
    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("ids");
        String[] id = ids.split(",");
        int sum = 0 ;
        for (String mId:id) {
            int i = service.deleteMember(Integer.parseInt(mId));
            sum+=i;
        }
        response.getWriter().print(""+sum);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kid = request.getParameter("kid");
        int mId = -1;
        if (!StringUtil.isEmpty(kid)){
            mId = Integer.parseInt(kid);
        }
        int i = service.deleteMember(mId);
        response.getWriter().print(""+i);
    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phonenumber = request.getParameter("phonenumber");
        String site = request.getParameter("site");

        Member member = new Member(username,password,email,phonenumber,site);
        int i = service.addMember(member);
        response.getWriter().print(""+i);
    }
    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*System.out.println("我进来了==query");*/
        PageBean pageBean = new PageBean();

        String pageIndex = request.getParameter("pageIndex");
        if (!StringUtil.isEmpty(pageIndex)){
            pageBean.setPageIndex(Integer.parseInt(pageIndex));
        }

        String pageCount = getInitParameter("pageCount");
        pageBean.setPageCount(Integer.parseInt(pageCount));
        pageBean.setCount(service.getCount());
        List<Member> memberList = service.queryPageList(pageBean);

        request.setAttribute("memberList",memberList);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("background/member/memberList.jsp").forward(request,response);
    }
}
