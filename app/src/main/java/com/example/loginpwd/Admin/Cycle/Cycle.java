package com.example.loginpwd.Admin.Cycle;

public class Cycle
{
    public  String uid;
    public String station;
    public String status;
    public String imageUrl;
    public int cycleRegNo;

    Cycle()
    {

    }

    public Cycle(String uid, String station, String status, String imageUrl, int cycleRegNo)
    {
        this.uid = uid;
        this.station = station;
        this.status = status;
        this.imageUrl = imageUrl;
        this.cycleRegNo = cycleRegNo;
    }

    public String toString()
    {
        return "ID : "+this.uid+" , STATION : "+this.station;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCycleRegNo() {
        return cycleRegNo;
    }

    public void setCycleRegNo(int cycleRegNo) {
        this.cycleRegNo = cycleRegNo;
    }
}
