package com.example.kienz.domaku;

public class koordinat {
    double latitude;
    double longitude;

    public koordinat() {
    }

    public koordinat(double latitude, double longitude) {
        this.latitude = latitude;

        this.longitude = longitude;
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
