package com.example.loginpwd.Admin.User;

public class User
{
    public String uid;
    public String name;
    public String emailId;
    public String gender;
    public String address;
    public String role;
    public long phone;
    public String password;

    User()
    {

    }


    public User(String uid, String name, String emailId, String gender, String address, String role, long phone)
    {
        this.uid = uid;
        this.name = name;
        this.emailId = emailId;
        this.gender = gender;
        this.address = address;
        this.role = role;
        this.phone = phone;

    }

    public User(String uid, String name, String emailId, String gender, String address, String role, long phone, String pwd)
    {
        this.uid = uid;
        this.name = name;
        this.emailId = emailId;
        this.gender = gender;
        this.address = address;
        this.role = role;
        this.phone = phone;
        password=pwd;
    }

    public String toString()
    {
        return "NAME : "+this.name+" , MAIL : "+this.emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
