package com.zr.bookstore.service;

import com.zr.bookstore.dao.CartDao;
import com.zr.bookstore.dao.daoimpl.CartDaoImpl;
import com.zr.bookstore.entity.Cart;

import java.util.List;

public  class CartService {
   private CartDao dao =  new CartDaoImpl();

    public List<Cart> findAll(){
        return  dao.findAll();
    }
    public int add(Cart cart){
        return dao.add(cart);
    }

    public int deleteAll() {
        return dao.deleteAll();
    }
}
