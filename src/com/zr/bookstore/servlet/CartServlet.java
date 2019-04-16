package com.zr.bookstore.servlet;
import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.Cart;
import com.zr.bookstore.service.BookService;
import com.zr.bookstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 购物车相关操作请求 
 * 1. 添加到购物车 
 * 2. 查看购物项列表 
 * 3. 删除某个购物项 
 * 4. 修改某个购物项中书的数量 
 * 5. 清空购物车
 */
@WebServlet(name = "CartServlet",urlPatterns = "/CartServlet")
public class CartServlet extends HttpServlet {
	private CartService cartService = new CartService();
	private BookService bookService = new BookService();
//	private static final long serialVersionUID = 1L;
//	private CartService cartService = new CartService();
//	private BookService bookService = new BookService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		if ("add".equals(action)){
			add(request,response);
		}else if ("query".equals(action)){
			query(request,response);
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException  {
		/*System.out.println("我进来了=====query");*/
		List<Cart> cartList = cartService.findAll();
		System.out.println(cartList);
		request.setAttribute("cartList",cartList);
		int sum = 0;
		for (Cart cart:cartList) {
			sum += cart.getPrice();
		}
		request.setAttribute("sum",sum);
		request.getRequestDispatcher("/font/cart.jsp").forward(request,response);
	}

	//添加购物车
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		/*System.out.println("我进来了=====Add");*/
		String  id = (String )request.getParameter("id");
		System.out.println(id);
		//通过Id查询到书的信息
		Book book = bookService.findBookById(Integer.parseInt(id));
		String bookName = book.getBookName();
		String author = book.getAuthor();
		int price = book.getPrice();
		int count = 1;
		Cart cart = new Cart(bookName, price, count, author);
		int add = cartService.add(cart);
		query(request,response);
	}


	public void deleteAll(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		cartService.deleteAll();
	}
}
