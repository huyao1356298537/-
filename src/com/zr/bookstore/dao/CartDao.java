package com.zr.bookstore.dao;

import com.zr.bookstore.entity.Cart;

import java.util.List;

public interface CartDao {

    List<Cart> findAll();

    int add(Cart cart);

    int deleteAll();
}
