package com.zr.bookstore.dao.daoimpl;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import com.zr.bookstore.dao.BookDao;
import com.zr.bookstore.entity.Book;
import com.zr.bookstore.entity.BookType;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDaoImpl implements BookDao {

     private QueryRunner qr = new QueryRunner();

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
                int issale = rs.getInt("issale");
                String press = rs.getString("press");
                Book book = new Book(bookId, bookName, author, price, sellcount, repertory,
                        typeId, imagepath, publicationDate, issale,press);
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
        String sql = "select * from book limit 0,12 ";
        return getBookList(sql);
    }


    //通过书籍类型查询书籍
    @Override
    public List<Book> findBookByType(int typeId) {
        String sql = "select * from book where type_id  = " +typeId;
        return getBookList(sql);
    }

    @Override
    public Book findBookById(int id) {
        String sql = " select * from book where book_id = "+id ;
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
                int issale = rs.getInt("issale");
                String press = rs.getString("press");
                Book book = new Book(bookId, bookName, author, price, sellcount, repertory,
                        typeId, imagepath, publicationDate, issale,press);
                return book;
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
        return null;
    }

    //查询新书  通过出版时间排序
    @Override
    public List<Book> findNewBook() {
        String sql = "select * from book  order by publication_date desc limit 0,15";
        return getBookList(sql);
    }
    //查询打折书
    @Override
    public List<Book> findSaleBook() {
        String sql = "select * from  book where issale = 1  limit 0,15";
        return getBookList(sql);
    }

    //查询3本打折书
    @Override
    public List<Book> findThreeSaleBook() {
        String sql = "select * from  book where issale = 1  limit 0,3";
        return getBookList(sql);
    }

    //查询热销书籍  通过销量
    @Override
    public List<Book> findHotBook() {
        String sql = "select * from  book  order by sellcount desc limit 0,15";
        return getBookList(sql);
    }

    //添加
    @Override
    public int add(Book book) {
        String sql = "INSERT INTO bookstore.book (book_name,author,price,sellcount,repertory,type_id," +
                "imagepath,publication_date,issale,press) VALUES ( ?,?,?,?,?,?,?,?,?,?); ";
        int i = 0;
        try {
            i = qr.update(JdbcUtils.getConnection(),sql,book.getBookName(),book.getAuthor(),book.getPrice(),book.getSellcount(),book.getRepertory(),book.getTypeId(),book.getImagepath(),book.getPublicationDate(),book.getissale(),book.getPress());
             return i ;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close();
        }
        return 0;
    }
    //删除
    @Override
    public int delete(int bookId) {
        String sql = "delete  from book where book_id = ? ";
        int i = 0;
        try {
             i = qr.update(JdbcUtils.getConnection(), sql, bookId);
            return i ;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close();
        }
        return 0;
    }
    //修改
    @Override
    public int update(Book book) {
        String sql = "update book set book_name=?, author=?, price=?, sellcount=?, repertory = ?, type_id=?, imagepath = ?, publication_date=?,issale=?,press=? where book_id = ?";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql, book.getBookName(),book.getAuthor(),book.getPrice(),book.getSellcount(),book.getRepertory(),book.getTypeId(),book.getImagepath(),book.getPublicationDate(),book.getissale(),book.getPress(),book.getBookId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public int getCount() {
        String sql = "select count(*) count from bookstore.book";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return 0;
    }


    @Override
    public List<Book> queryBookPage(PageBean pageBean) {
        String sql="select * from book order by book_id limit ?,?";
        List<Book> list =new ArrayList<>();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
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
                int issale = rs.getInt("issale");
                String press = rs.getString("press");
                Book book = new Book(bookId, bookName, author, price, sellcount, repertory,
                        typeId, imagepath, publicationDate, issale,press);
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

    @Override
    public int findBookCountByType(int typeId) {
        String sql = "select count(*) count from book where type_id=?";
        PreparedStatement ps =null;
        ResultSet rs =null;

        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,typeId);
            rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt("count");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if (ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return 0 ;
    }

    //通过书名查询书籍
    @Override
    public Book findBookByName(String bookName) {
        String sql = " select * from book where book_name = ?" ;
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,bookName);
            rs = ps.executeQuery();
            while (rs.next()){
                int bookId = rs.getInt("book_id");
                String bookName1 = rs.getString("book_name");
                String author = rs.getString("author");
                int price = rs.getInt("price");
                int sellcount = rs.getInt("sellcount");
                int repertory = rs.getInt("repertory");
                int typeId = rs.getInt("type_id");
                String imagepath = rs.getString("imagepath");
                Date publicationDate = rs.getDate("publication_date");
                int issale = rs.getInt("issale");
                String press = rs.getString("press");
                Book book = new Book(bookId, bookName1, author, price, sellcount, repertory,
                        typeId, imagepath, publicationDate, issale,press);
                return book;
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
        return null;
    }

    @Override
    public int udateSell(String Name, int sellcount, int repertory) {
        String sql = "update book set sellcount=? ,repertory=? where book_name=?";
        int i = 0;
        try {
            i= qr.update(JdbcUtils.getConnection(), sql, sellcount, repertory, Name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close();
        }
        return  i ;
    }
}
