package com.zr.bookstore.dao;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.BookType;
import com.zr.bookstore.entity.Link;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

public interface BookTypeDao {

    public List<BookType> findAll();

    public BookType findTypeById(int typeId);

    public int getCount();

    public int add(BookType bookType);

    public int delete(int typeId);

    public int update(BookType bookType);

    public List<BookType> queryBookTypePage(PageBean pageBean);

}
