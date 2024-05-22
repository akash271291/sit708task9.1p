package com.example.akash_task9;

public class ItemRecord {
    private String title;
    private String description;
    private String phone;
    private String date;
    private String location;
    private double latitude;
    private double longitude;
    public ItemRecord(String title, String description, String phone, String date, String location,
                      double latitude, double longitude) {
        this.title = title;
        this.description = description;
        this.phone = phone;
        this.date = date;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
}
