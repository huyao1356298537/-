package com.zr.bookstore.servlet;

import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.Cart;
import com.zr.bookstore.entity.Order;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.service.BookService;
import com.zr.bookstore.service.CartService;
import com.zr.bookstore.service.OrderService;
import com.zr.bookstore.utils.DateUtil;
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

@WebServlet(name = "OrderServlet" ,urlPatterns = "/OrderServlet" ,initParams = {@WebInitParam(name ="pageCount" ,value = "5")})
public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private CartService cartService = new CartService();
    private BookService bookService = new BookService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        if ("add".equals(action)){
            add(request,response);
        }if ("query".equals(action)){
            query(request,response);
        }
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
        pageBean.setCount(orderService.getCount());
        //获取数据
        List<Order> orderList = orderService.queryOrderPage(pageBean);
        request.setAttribute("orderList",orderList);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("/background/order/orderlist.jsp").forward(request,response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*System.out.println("我进来了====add");*/
        String amount = request.getParameter("amount");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String bookName = request.getParameter("bookName");
//        System.out.println(oid);
//        System.out.println(name);
//        System.out.println(phoneNumber);
         String currentDateStr = null;
        try {
            currentDateStr = DateUtil.getCurrentDateStr();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = DateUtil.formatString(currentDateStr, "yyyy-MM-dd hh:mm:ss");
        String orderId = StringUtil.autoOrderId();
        //添加订单到数据库
        Order  order = new Order(orderId,amount, name, phoneNumber,date);
        int add = orderService.add(order);
//        System.out.println("add====="+add);
        if(add==1){
            List<Cart> cartList = cartService.findAll();
            //库存、销量动态改变
            for (Cart cart:cartList) {
                Book book = bookService.findBookByName(cart.getBookName());
                System.out.println("网宿科技德哈卡SD卡书的"+book);
                int sellcount = book.getSellcount()+1;
                int repertory = book.getRepertory()-1;
                if (sellcount>=0&&repertory>=0){
                    bookService.updateSell(cart.getBookName(),sellcount,repertory);
                }else{
                    String msg = "下手太慢，没货了！！！";
                    request.setAttribute("msg",msg);
                    request.getRequestDispatcher("/MainServlet").forward(request,response);
                }
            }
            //清空购物车
            int i = cartService.deleteAll();
            if (i==0){
                //重新查询购物车表，并跳转到购物车
                queryBBB(request,response);
            }
        }
    }

    private void queryBBB(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException  {
        System.out.println("我进来了=====query");
        List<Cart> cartList = cartService.findAll();
        System.out.println(cartList);
        request.setAttribute("cartList",cartList);
        int sum = 0;
        for (Cart cart:cartList) {
            sum += cart.getPrice();
        }
        request.setAttribute("sum",sum);
        request.getRequestDispatcher("/MainServlet").forward(request,response);
    }
}
