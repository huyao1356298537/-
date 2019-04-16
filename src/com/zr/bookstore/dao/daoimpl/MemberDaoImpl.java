package com.zr.bookstore.dao.daoimpl;

import com.zr.bookstore.dao.MemberDao;
import com.zr.bookstore.entity.Member;
import com.zr.bookstore.entity.PageBean;
import com.zr.bookstore.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
    private QueryRunner qr =  new QueryRunner();

    @Override
    public List<Member> findAll() {
        List<Member> list  = new ArrayList<>();

        String sql = "select * from member order by m_id";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int mId = rs.getInt("m_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phonenumber = rs.getString("phonenumber");
                String site = rs.getString("site");
                Member member = new Member(mId,username,password,email,phonenumber,site);
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null)
                    rs.close();
                if (ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return null;
    }

    @Override
    public int getCount() {
        String sql = "select count(*) count from member";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
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
                if (rs!=null)
                    rs.close();
                if (ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return 0;
    }

    @Override
    public List<Member> queryPageList(PageBean pageBean) {
        List<Member> list = new ArrayList<>();
        String sql = "select *from member order by m_id limit ?,?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                int mId = rs.getInt("m_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phonenumber = rs.getString("phonenumber");
                String site = rs.getString("site");
                Member member = new Member(mId,username,password,email,phonenumber,site);
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null)
                    rs.close();
                if (ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return list;
    }

    @Override
    public int addMember(Member member) {
        String sql = "insert into member(username, password, email, phonenumber, site) VALUES (?,?,?,?,?)";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql,
                    member.getUsername(), member.getPassword(), member.getEmail(), member.getPhonenumber(), member.getSite());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteMember(int mId) {
        String sql = "delete from member where m_id = ?";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql, mId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateMember(Member member) {
        String sql = "update member set  username = ? , password = ? , email = ? , phonenumber = ? , site = ? where m_id=?";
        try {
            int i = qr.update(JdbcUtils.getConnection(), sql,
                    member.getUsername(), member.getPassword(), member.getEmail(),
                    member.getPhonenumber(), member.getSite(),member.getmId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Member queryOne(int mId) {
        String sql = "select *from member where m_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,mId);
            rs = ps.executeQuery();
            while (rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phonenumber = rs.getString("phonenumber");
                String site = rs.getString("site");
                Member member = new Member(mId,username,password,email,phonenumber,site);
                return member;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null)
                    rs.close();
                if (ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return null;
    }

    @Override
    public Member queryOne(String username) {
        String sql="select * from member where username = ?";
        try {
            Member member = qr.query(JdbcUtils.getConnection(), sql, new BeanHandler<>(Member.class), username);
            return member;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
