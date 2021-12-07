package com.example.loginpwd.Admin.Station;

public class Station
{

    public String sid;
    public String stationName;
    public double latitude;
    public double longitude;
    public String description;
    public String openingTime;
    public String closingTime;
    public String conductedBy;
    public int noOfCycles;
    public int availableCycles;

    Station()
    {

    }



    public Station(String sid, String stationName, double latitude, double longitude, String description, String openingTime, String closingTime, String ConductedBy, int noOfCycles, int availableCycles) {
        this.sid = sid;
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.conductedBy = ConductedBy;
        this.noOfCycles = noOfCycles;
        this.availableCycles = availableCycles;
    }


    public String toString()
    {
        return "NAME : "+this.stationName+" ,ID : "+this.sid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getConductedBy() {
        return conductedBy;
    }

    public void setConductedBy(String conductedBy) {
        this.conductedBy = conductedBy;
    }

    public int getNoOfCycles() {
        return noOfCycles;
    }

    public void setNoOfCycles(int noOfCycles) {
        this.noOfCycles = noOfCycles;
    }

    public int getAvailableCycles() {
        return availableCycles;
    }

    public void setAvailableCycles(int availableCycles) {
        this.availableCycles = availableCycles;
    }
}

