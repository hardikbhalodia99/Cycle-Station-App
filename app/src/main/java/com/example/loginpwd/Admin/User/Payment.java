package com.example.loginpwd.Admin.User;

public class Payment
{

    String payId;
    String bookId;
    int paid;
    int totalpay;

    public Payment(String payId, String bookId, int paid, int totalpay) {
        this.payId = payId;
        this.bookId = bookId;
        this.paid = paid;
        this.totalpay = totalpay;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getTotalpay() {
        return totalpay;
    }

    public void setTotalpay(int totalpay) {
        this.totalpay = totalpay;
    }
}
