package com.selepdf.buyaround.model;

public class UserRadius {
    private double latitude;
    private double altitude;
    private int radiusMeters;


    public UserRadius(double latitude, double altitude, int radiusMeters) {
        this.latitude = latitude;
        this.altitude = altitude;
        this.radiusMeters = radiusMeters;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public int getRadiusMeters() {
        return radiusMeters;
    }

    public void setRadiusMeters(int radiusMeters) {
        this.radiusMeters = radiusMeters;
    }
}
