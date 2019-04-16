package com.zr.bookstore.dao.daoimpl;
/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import com.zr.bookstore.dao.BookTypeDao;
import com.zr.bookstore.entity.BookType;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeDaoImpl implements BookTypeDao {
    QueryRunner qr = new QueryRunner();

    //通过SQL语句 获得 BookType集合
    public List<BookType> getBookTypeList(String sql) {
        List<BookType> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int typeId = rs.getInt("type_id");
                String typeName = rs.getString("type_name");
                BookType bookType = new BookType(typeId, typeName);
                list.add(bookType);
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
        return list;
    }


    @Override
    public List<BookType> findAll() {
        String sql = "select * from booktype";
        return getBookTypeList(sql);
    }

    @Override
    public int getCount() {
        String sql = "select count(*) count from bookstore.booktype";
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
    public BookType findTypeById(int typeId) {
        String sql = "select * from booktype where type_id=" + typeId;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String typeName = rs.getString("type_name");
                BookType bookType = new BookType(typeId, typeName);
                return bookType;
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
        return null;
    }

    @Override
    public int add(BookType bookType) {
        String sql = "insert into booktype (type_name) values(?)";
        int i = 0;
        try {
            i = qr.update(JdbcUtils.getConnection(), sql,
                    bookType.getTypeName());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public int update(BookType bookType) {
        String sql = "update booktype set type_name=? where type_id = ?";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql, bookType.getTypeName(),bookType.getTypeId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public int delete(int typeId) {
        String sql = "delete from booktype where type_id=?";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql, typeId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public List<BookType> queryBookTypePage(PageBean pageBean) {
        String sql="select * from booktype limit ?,?";
        List<BookType> list =new ArrayList<>();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                int typeId = rs.getInt("type_id");
                String typeName = rs.getString("type_name");
                BookType bookType = new BookType();
                bookType.settype_id(typeId);
                bookType.settype_name(typeName);
                list.add(bookType);
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


}
