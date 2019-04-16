package com.zr.bookstore.dao;

import com.zr.bookstore.entity.Member;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

public interface MemberDao {

    public List<Member> findAll();

    public int getCount();

    public List<Member> queryPageList(PageBean pageBean);

    public int addMember(Member member);

    public int deleteMember(int mId);

    public int updateMember(Member member);

    public Member queryOne(int mId);

    public Member queryOne(String username);
}
