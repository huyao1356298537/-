package com.zr.bookstore.dao;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

public interface BookDao {

    //查询
    //查询全部图书
    public List<Book> findAll();

    //通过类型查询所有书籍

    public List<Book> findBookByType(int typeId);

    //通过Id查询书籍
    public Book findBookById(int Id);

    //查新书，通过出版时间排序
    public List<Book> findNewBook();

    //查促销书，
    public List<Book> findSaleBook();

    //查3本促销书，
    public List<Book> findThreeSaleBook();

    //查热销书,通过销量排序
    public List<Book> findHotBook();

    //添加
    public int add(Book book);
    //删除
    public int delete(int boodId);
    //修改
    public int update(Book  book);

    public int getCount();

    public List<Book> queryBookPage(PageBean pageBean);

    public int findBookCountByType(int typeId);


    //通过书名查询书籍
    public Book findBookByName(String bookName);
    //修改库存
    int udateSell(String Name ,int sellcount, int repertory);
}
