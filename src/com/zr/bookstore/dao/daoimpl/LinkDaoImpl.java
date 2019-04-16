package com.zr.bookstore.dao.daoimpl;

import com.zr.bookstore.dao.LinkDao;
import com.zr.bookstore.entity.Link;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mr.Xu
 * @Date: 2019/3/11 0011
 */
public class LinkDaoImpl implements LinkDao {
    QueryRunner qr =new QueryRunner();
    @Override
    public List<Link> findAll() {
        List<Link> list =new ArrayList<>();
        String sql="select * from bookstore.link order by link_order";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int linkId = rs.getInt("link_id");
                String linkName = rs.getString("link_name");
                String email = rs.getString("email");
                String linkUrl = rs.getString("link_url");
                int linkOrder = rs.getInt("link_order");
                Link link =  new Link(linkId,linkName,email,linkUrl,linkOrder);
                list.add(link);
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
        return  list;
    }

    @Override
    public int  getCount() {
        String sql="select count(*) count from bookstore.link";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = connection.prepareStatement(sql);
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
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public List<Link> queryLinkPage(PageBean pageBean) {
        String sql="select * from bookstore.link order by link_order limit ?,?";
        List<Link> list =new ArrayList<>();
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                int linkId = rs.getInt("link_id");
                String linkName = rs.getString("link_name");
                String email = rs.getString("email");
                String linkUrl = rs.getString("link_url");
                int linkOrder = rs.getInt("link_order");
                Link link =  new Link(linkId,linkName,email,linkUrl,linkOrder);
                list.add(link);
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
    public int addLink(Link link) {
        String sql="insert into bookstore.link (link_name,email,link_url,link_order) values(?,?,?,?)";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql,
                    link.getLinkName(), link.getEmail(), link.getLinkUrl(), link.getLinkOrder());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public int update(Link link) {
        String sql="update link set link_name=?,email=?,link_url=?,link_order=? where link_id=?";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql,
                    link.getLinkName(), link.getEmail(), link.getLinkUrl(), link.getLinkOrder(),link.getLinkId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public int delete(int linkId) {
        String sql="delete from bookstore.link where link_id=?";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql, linkId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public Link queryOne(int linkId) {
        String sql="select * from bookstore.link where link_id=?";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,linkId);
            rs = ps.executeQuery();
            while (rs.next()){
                String linkName = rs.getString("link_name");
                String email = rs.getString("email");
                String linkUrl = rs.getString("link_url");
                int linkOrder = rs.getInt("link_order");
                Link link =  new Link(linkId,linkName,email,linkUrl,linkOrder);
                return link;
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
        return  null;
    }



}
