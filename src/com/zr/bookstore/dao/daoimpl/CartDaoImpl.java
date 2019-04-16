package com.zr.bookstore.dao.daoimpl;

import com.zr.bookstore.dao.CartDao;
import com.zr.bookstore.entity.Cart;
import com.zr.bookstore.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private QueryRunner qr = new QueryRunner();

    //通过SQL语句 获得 Cart 集合
    public List<Cart> getCartList(String sql) {
        List<Cart> list = new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                int price = rs.getInt("price");
                int count = rs.getInt("count");
                Cart cart = new Cart(id, bookName, price, count, author);
                list.add(cart);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return null;
    }
    @Override
    public List<Cart> findAll() {
        String sql = "select * from cart ";
        return getCartList(sql);
    }

    @Override
    public int add(Cart cart) {
        String sql="insert into bookstore.cart (book_name,price,count,author) values(?,?,?,?)";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql,
                    cart.getBookName(), cart.getPrice(), cart.getCount(), cart.getAuthor());
            System.out.println(cart.getBookName());
            System.out.println(cart.getPrice());
            System.out.println(cart.getCount());
            System.out.println(cart.getAuthor());
            System.out.println(i);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close();
        }
        return 0;
    }
    @Override
    public int deleteAll(){
        String sql = " TRUNCATE  TABLE  cart";
        int i = 0;
        try {
            i = qr.update(JdbcUtils.getConnection(),sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close();
        }
        return  i;
    }
}
