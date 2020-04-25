package com.example.zoudiy.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TripInfo {
    @SerializedName("areasCovered")
    @Expose
    String[] areasCovered;

    @SerializedName("kids")
    @Expose
    ArrayList<Kid> kids;

    @SerializedName("_id")
    @Expose
    String tripId;

    @SerializedName("destination")
    @Expose
    TripAddress dest;

    @SerializedName("city")
    @Expose
    String cityId;

    @SerializedName("weeklySchedule")
    @Expose
    String weeklySchedule;

    @SerializedName("tripType")
    @Expose
    String tripType;

    @SerializedName("startTime")
    @Expose
    String startTime;

    @SerializedName("vehicleId")
    @Expose
    Vehicle vehicle;

    @SerializedName("capacity")
    @Expose
    String capacity;

    public String getCapacity() {
        return capacity;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String[] getAreasCovered() {
        return areasCovered;
    }

    public ArrayList<Kid> getKids() {
        return kids;
    }

    public String getTripId() {
        return tripId;
    }

    public TripAddress getDest() {
        return dest;
    }

    public String getCityId() {
        return cityId;
    }

    public String getWeeklySchedule() {
        return weeklySchedule;
    }

    public String getTripType() {
        return tripType;
    }

    public String getStartTime() {
        return startTime;
    }
}
