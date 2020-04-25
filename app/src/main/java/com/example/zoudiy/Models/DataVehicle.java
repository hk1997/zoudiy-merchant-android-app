package com.example.zoudiy.Models;

import com.example.zoudiy.utils.Vehicle;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataVehicle {
    @SerializedName("vehicles")
    @Expose
    private ArrayList<Vehicle> vehicleList;


    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setKidList(ArrayList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

}
