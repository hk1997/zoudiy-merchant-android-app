package com.example.zoudiy.utils;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("regions")
    @Expose
    private String[] regions;

    public City(String id, String name, String state, String[] regions) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.regions = regions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String[] getRegions() {
        return regions;
    }

    @NonNull
    @Override
    public String toString() {
        return name + "[" + state + "]";
    }
}
