package com.zr.bookstore.entity;

public class Cart {
    private int id;
    private String bookName;
    private int price;
    private int count;
    private String author;

    public Cart(String bookName, int price, int count,  String author) {
        this.bookName = bookName;
        this.price = price;
        this.count = count;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", author='" + author + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Cart(int id, String bookName, int price, int count,  String author) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.count = count;
        this.author = author;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
