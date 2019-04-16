package com.zr.bookstore.dao;

import com.zr.bookstore.entity.Order;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

public interface OrderDao {
    int add(Order order);

    List<Order> findAll();

    int getCount();

    List<Order> queryOrderPage(PageBean pageBean);
}
