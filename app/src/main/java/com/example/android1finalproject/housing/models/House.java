package com.example.android1finalproject.housing.models;

import com.google.firebase.firestore.GeoPoint;

public class House {
    GeoPoint geoPoint;
    String name;
    double price;
    String type;

    public House() {
    }

    public House(GeoPoint geoPoint, String name, double price, String type) {
        this.geoPoint = geoPoint;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public GeoPoint getGeoPoint() { return geoPoint; }

    public void setGeoPoint(GeoPoint geoPoint) { this.geoPoint = geoPoint; }

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

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}
