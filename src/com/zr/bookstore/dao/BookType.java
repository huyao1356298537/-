package com.zr.bookstore.dao;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import java.util.List;

public interface BookType {

    public List<BookType> findAll();

    public BookType findTypeById(int typeId);


}
