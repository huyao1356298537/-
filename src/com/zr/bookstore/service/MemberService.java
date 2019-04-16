package com.zr.bookstore.service;

import com.zr.bookstore.dao.MemberDao;
import com.zr.bookstore.dao.daoimpl.MemberDaoImpl;
import com.zr.bookstore.entity.Member;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

public class MemberService {
    private MemberDao dao = new MemberDaoImpl();
    public List<Member> findAll(){
        return dao.findAll();
    }

    public int updateMember(Member member) {
        return dao.updateMember(member);
    }

    public Member queryOne(int mId) {
        return dao.queryOne(mId);
    }

    public int deleteMember(int parseInt) {
        return dao.deleteMember(parseInt);
    }

    public int addMember(Member member) {
        return dao.addMember(member);
    }

    public int getCount() {
        return dao.getCount();
    }

    public List<Member> queryPageList(PageBean pageBean) {
        return dao.queryPageList(pageBean);
    }
    public Member queryOneByName(String username){
        return dao.queryOne(username);
    }
}
