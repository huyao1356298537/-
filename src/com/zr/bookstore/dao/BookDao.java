package com.zr.bookstore.dao;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import com.zr.bookstore.entity.Book;

import java.util.List;

public interface BookDao {

    //查询
    //查询全部图书
    public List<Book> findAll();

    //通过类型查询所有书籍

    public List<Book> findBookByType(int typeId);

    //查新书
    public List<Book> findNewBook();
    //查促销书
    public List<Book> findDisBookBy

    //添加
    public int add();
    //删除
    public int delete();
    //修改
    public int update();

}
