package com.example.zoudiy.utils;

public class AddTripClass {
    private String vehicleId;
    private String city;
    private String destination;
    private String weeklySchedule;
    private String[] areasCovered;
    private String tripType;
    private String capacity;
    private String startTime;
    private String token;

    public AddTripClass(String vehicleId, String city, String destination, String weeklySchedule, String[] areasCovered, String tripType, String capacity, String startTime, String token) {
        this.vehicleId = vehicleId;
        this.city = city;
        this.destination = destination;
        this.weeklySchedule = weeklySchedule;
        this.areasCovered = areasCovered;
        this.tripType = tripType;
        this.capacity = capacity;
        this.startTime = startTime;
        this.token = token;
    }

}
