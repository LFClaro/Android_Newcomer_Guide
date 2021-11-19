package com.example.android1finalproject.housing.models;

public class House {
    double latitude;
    double longitude;
    String name;
    double price;

    public House() {
    }

    public House(double latitude, double longitude, String name, double price) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
