package com.zr.bookstore.service;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import com.zr.bookstore.dao.BookDao;
import com.zr.bookstore.dao.daoimpl.BookDaoImpl;
import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

public class BookService {

    private BookDao dao = new BookDaoImpl();

    //查询全部图书
    public List<Book> findAll(){
        return dao.findAll();
    }

    //通过类型查询所有书籍

    public List<Book> findBookByType(int typeId){
        return dao.findBookByType(typeId);
    }

    //通过Id查询书籍

    public Book findBookById(int bookId){return dao.findBookById(bookId);}

    //查新书，通过出版时间排序
    public List<Book> findNewBook(){
        return dao.findNewBook();
    }
    //查促销书，
    public List<Book> findSaleBook(){
        return dao.findSaleBook();
    }

    //查3本促销书，
    public List<Book> findThreeSaleBook(){
        return dao.findThreeSaleBook();
    }

    //查热销书,通过销量排序
    public List<Book> findHotBook(){
        return dao.findHotBook();
    }
    //添加
    public int add(Book book){
        return dao.add(book);
    }
    //删除
    public int delete(int boodId){
        return dao.delete(boodId);
    }
    //修改
    public int update(Book book){
        return dao.update(book);
    }

    public int getCount(){return dao.getCount();}

    public List<Book> queryBookPage(PageBean pageBean){
        return dao.queryBookPage(pageBean);
    }

    //通过书名查询书籍
    public Book findBookByName(String bookName){
        return dao.findBookByName(bookName);
    }

    public int updateSell(String Name, int sellcount, int repertory){
        return dao.udateSell(Name,sellcount,repertory);
    }
}
