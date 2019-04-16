package com.zr.bookstore.dao;

import com.zr.bookstore.entity.Link;
import com.zr.bookstore.entity.PageBean;

import java.util.List;

/**
 * @author: Mr.Xu
 * @Date: 2019/3/11 0011
 */
public interface LinkDao {
    public List<Link> findAll();

    public int getCount();

    public List<Link> queryLinkPage(PageBean pageBean);

    public int addLink(Link link);

    public int update(Link link);

    public int delete(int linkId);

    public Link queryOne(int linkId);


}
