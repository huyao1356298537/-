package com.zr.bookstore.entity;/*
 * @author:张尧俊
 * date :2019/3/26 0026
 */


public class BookType {

    private int typeId;
    private String typeName;

    public BookType() {
    }

    public BookType(String typeName) {
        this.typeName = typeName;
    }

    public BookType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void settype_id(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void settype_name(String typeName) {
        this.typeName = typeName;
    }
}
