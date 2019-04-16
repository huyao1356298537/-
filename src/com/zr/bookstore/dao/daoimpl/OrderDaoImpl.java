package com.zr.bookstore.dao.daoimpl;

import com.zr.bookstore.dao.OrderDao;
import com.zr.bookstore.entity.Order;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
      private QueryRunner qr =  new QueryRunner();
    @Override
    public int add(Order order) {
        String sql="insert into bookstore.order (order_id,amount,receiver,phonenumber,order_time) values(?,?,?,?,?)";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql,
                    order.getOrderId(),  order.getAmount(),order.getReceiver(),order.getPhonenumber(),order.getOrderTime());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close();
        }
        return 0;
    }

    //通过SQL语句 获得 Order 集合
    public List<Order> getOrderList(String sql) {
        List<Order> list = new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String id = rs.getString("order_id");
                String amount = rs.getString("amount");
                String receiver = rs.getString("receiver");
                String phonenumber = rs.getString("phonenumber");
                Date orderTime = rs.getDate("order_time");
                Order order = new Order(id, amount, receiver, phonenumber,orderTime);
                list.add(order);
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
    public List<Order> findAll() {
        String sql = "selecte * from order";
        return getOrderList(sql);
    }

    @Override
    public int getCount() {
        String sql = "select count(*) count from bookstore.order ";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return 0;


    }

    @Override
    public List<Order> queryOrderPage(PageBean pageBean) {
        String sql="select * from bookstore.order order by order_id limit ?,?";
        List<Order> query = null;
        try {
            query = qr.query(JdbcUtils.getConnection(), sql, new BeanListHandler<>(Order.class), pageBean.getIndex(), pageBean.getPageCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
//        List<Order> list =new ArrayList<>();
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//        try {
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1,pageBean.getIndex());
//            ps.setInt(2,pageBean.getPageCount());
//            rs = ps.executeQuery();
//            while (rs.next()){
//                String id = rs.getString("order_id");
//                String amount = rs.getString("amount");
//                String receiver = rs.getString("receiver");
//                String phonenumber = rs.getString("phonenumber");
//                Date orderTime = rs.getDate("order_time");
//                System.out.println("time========");
//                Order order = new Order(id, amount, receiver, phonenumber,orderTime);
//                list.add(order);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(rs!=null)
//                    rs.close();
//                if(ps!=null)
//                    ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            JdbcUtils.close();
//        }
//        return list;

    }
}
