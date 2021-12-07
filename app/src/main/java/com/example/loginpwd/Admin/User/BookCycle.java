package com.example.loginpwd.Admin.User;

public class BookCycle
{
    String bookId;
    String stationId;
    String userId;
    String startTime;
    String EndTime;
    int duration;

    public BookCycle(String bookId, String stationId, String userId, String startTime, String endTime, int duration) {
        this.bookId = bookId;
        this.stationId = stationId;
        this.userId = userId;
        this.startTime = startTime;
        EndTime = endTime;
        this.duration = duration;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
