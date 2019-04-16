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
    private Date publicationDate;
    private int issale;
    private String press;

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setpublication_date(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPress() {
        return press;
    }

    public void setpress(String press) {
        this.press = press;
    }


    public int getissale() {
        return issale;
    }

    public void setissale(int issale) {
        this.issale = issale;
    }

    public int getBookId() {
        return bookId;
    }

    public void setbook_id(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setbook_id(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setauthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setprice(int price) {
        this.price = price;
    }

    public int getSellcount() {
        return sellcount;
    }

    public void setsellcount(int sellcount) {
        this.sellcount = sellcount;
    }

    public int getRepertory() {
        return repertory;
    }

    public void setrepertory(int repertory) {
        this.repertory = repertory;
    }

    public int getTypeId() {
        return typeId;
    }

    public void settype_id(int typeId) {
        this.typeId = typeId;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setimagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public Book() {
    }

    public Book(int bookId, String bookName, String author, int price, int sellcount, int repertory, int typeId, String imagepath, Date publicationDate, int issale, String press) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.sellcount = sellcount;
        this.repertory = repertory;
        this.typeId = typeId;
        this.imagepath = imagepath;
        this.publicationDate = publicationDate;
        this.issale = issale;
        this.press = press;
    }

    public Book(String bookName, String author, int price, int sellcount, int repertory, int typeId, String imagepath, Date publicationDate, int issale, String press) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.sellcount = sellcount;
        this.repertory = repertory;
        this.typeId = typeId;
        this.imagepath = imagepath;
        this.publicationDate = publicationDate;
        this.issale = issale;
        this.press = press;
    }
}
