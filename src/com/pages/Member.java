package com.pages;

import java.util.Date;

public class Member {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String group;
    private Date birth;
    private Date date;

    public Member(int id, String name, String phone, String email, String group, Date birth, Date date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.group = group;
        this.birth = birth;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
