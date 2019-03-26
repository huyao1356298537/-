package com.zr.bookstore.dao.daoimpl;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import com.zr.bookstore.dao.BookDao;
import com.zr.bookstore.entity.Book;
import com.zr.bookstore.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDaoImpl implements BookDao {


    //通过SQL语句 获得 BOOK 集合
    public List<Book> getBookList(String sql) {
        List<Book> list = new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int bookId = rs.getInt("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                int price = rs.getInt("price");
                int sellcount = rs.getInt("sellcount");
                int repertory = rs.getInt("repertory");
                int typeId = rs.getInt("type_id");
                String imagepath = rs.getString("imagepath");
                Date publicationDate = rs.getDate("publication_date");
                int isdiscount = rs.getInt("isdiscount");
                Book book = new Book(bookId, bookName, author, price, sellcount, repertory,
                        typeId, imagepath, publicationDate, isdiscount);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return list;
    }

    //查询
    @Override
    public List<Book> findAll() {
        String sql = "sel";
        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement ps = connection.prepareStatement(sql);
        return null;
    }

    @Override
    public List<Book> findBookByType(int typeId) {
        String sql = "sel";
        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement ps = connection.prepareStatement(sql);

        return null;
    }

    //添加
    @Override
    public int add() {

        return 0;
    }

    //删除
    @Override
    public int delete() {

        return 0;
    }

    //修改
    @Override
    public int update() {

        return 0;
    }
}
