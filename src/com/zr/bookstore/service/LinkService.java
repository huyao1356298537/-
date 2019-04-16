package com.zr.bookstore.service;

import com.zr.bookstore.dao.LinkDao;
import com.zr.bookstore.dao.daoimpl.LinkDaoImpl;
import com.zr.bookstore.entity.Link;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

/**
 * @author: Mr.Xu
 * @Date: 2019/3/11 0011
 */
public class LinkService {
    private LinkDao dao=new LinkDaoImpl();
    public List<Link> findAll(){
        return dao.findAll();
    }

    public int getCount(){return dao.getCount();}

    public List<Link> queryLinkPage(PageBean pageBean){
        return dao.queryLinkPage(pageBean);
    }
    public int addLink(Link link){
        return dao.addLink(link);
    }
    public int delete(int linkId){return dao.delete(linkId);}
    public Link queryOne(int linkId){return dao.queryOne(linkId);}
    public int update(Link link){return dao.update(link);}

}


