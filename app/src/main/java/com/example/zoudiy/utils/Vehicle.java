package com.example.zoudiy.utils;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle {


    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("vehicleNo")
    @Expose
    private String vehicleNo;

    @SerializedName("images")
    @Expose
    private String[] images;

    @SerializedName("_id")
    @Expose
    private String _id;


    public Vehicle(String type, String vehicleNo) {
        this.type = type;
        this.vehicleNo = vehicleNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String get_id() {
        return _id;
    }

    @NonNull
    @Override
    public String toString() {
        return this.vehicleNo + " [" + this.type + "]";
    }
}
