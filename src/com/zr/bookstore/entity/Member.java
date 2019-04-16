package com.zr.bookstore.entity;

public class Member {
    private int mId;
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private String site;

    public Member(String username, String password, String email, String phonenumber, String site) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.site = site;
    }

    public Member(int mId, String username, String password, String email, String phonenumber, String site) {
        this.mId = mId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.site = site;
    }

    public Member() {
    }

    public int getmId() {
        return mId;
    }

    public void setm_id(int mId) {
        this.mId = mId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

}
