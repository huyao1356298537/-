package com.zr.bookstore.entity;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */

import java.util.Date;

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private int price;
    private int sellcount;
    private int repertory;
    private int typeId;
    private String imagepath;
    private Date publicationTime;
    private int isdiscount;

    public Date getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(Date publicationTime) {
        this.publicationTime = publicationTime;
    }

    public int getIsdiscount() {
        return isdiscount;
    }

    public void setIsdiscount(int isdiscount) {
        this.isdiscount = isdiscount;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSellcount() {
        return sellcount;
    }

    public void setSellcount(int sellcount) {
        this.sellcount = sellcount;
    }

    public int getRepertory() {
        return repertory;
    }

    public void setRepertory(int repertory) {
        this.repertory = repertory;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public Book() {
    }

    public Book(int bookId, String bookName, String author, int price, int sellcount, int repertory,
                int typeId, String imagepath, Date publicationTime, int isdiscount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.sellcount = sellcount;
        this.repertory = repertory;
        this.typeId = typeId;
        this.imagepath = imagepath;
        this.publicationTime = publicationTime;
        this.isdiscount = isdiscount;
    }

    public Book(String bookName, String author, int price, int sellcount, int repertory,
                int typeId, String imagepath, Date publicationTime, int isdiscount) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.sellcount = sellcount;
        this.repertory = repertory;
        this.typeId = typeId;
        this.imagepath = imagepath;
        this.publicationTime = publicationTime;
        this.isdiscount = isdiscount;
    }
}
