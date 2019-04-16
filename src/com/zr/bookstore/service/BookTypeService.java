package com.zr.bookstore.service;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import com.zr.bookstore.dao.BookTypeDao;
import com.zr.bookstore.dao.daoimpl.BookTypeDaoImpl;
import com.zr.bookstore.entity.BookType;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

public class BookTypeService {

    private BookTypeDao dao = new BookTypeDaoImpl();

    //查询全部图书类型
    public List<BookType> findAll(){
        return dao.findAll();
    }
    //    查询一个
    public BookType queryOne(int typeId){return dao.findTypeById(typeId);}
    //  获取总条数
    public int getCount(){return dao.getCount();}
    //
    public List<BookType> queryBookTypePage(PageBean pageBean){
        return dao.queryBookTypePage(pageBean);
    }

    public int add(BookType bookType){
        return  dao.add(bookType);
    }

    public int delete(int typeId){
        return dao.delete(typeId);
    }

    public int update(BookType bookType) {
        return dao.update(bookType);
    }

    public BookType findTypeById(int typeId){
        return dao.findTypeById(typeId);
    }

}
