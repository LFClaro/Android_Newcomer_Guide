package com.example.android1finalproject.food.models;

import com.google.type.DateTime;

import java.util.Date;

public class Coupon {
    String name;
    int percentage;
    Date validUntil;
    String website;

    public Coupon(String name, int percentage, Date validUntil, String website) {
        this.name = name;
        this.percentage = percentage;
        this.validUntil = validUntil;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
