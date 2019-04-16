package com.zr.bookstore.service;

import com.zr.bookstore.dao.daoimpl.OrderDaoImpl;
import com.zr.bookstore.entity.Order;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

public class OrderService {
   private OrderDaoImpl dao =  new OrderDaoImpl();

   public int add(Order order){
       return dao.add(order);
   }

   public List<Order> findAll(){return dao.findAll();}

    public int getCount() {
        return dao.getCount();
    }

    public List<Order> queryOrderPage(PageBean pageBean) {
        return dao.queryOrderPage(pageBean);
    }
}
