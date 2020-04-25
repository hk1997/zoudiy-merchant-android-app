package com.example.zoudiy.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Coordinates {
    @SerializedName("lat")
    @Expose
    String lat;

    @SerializedName("lng")
    @Expose
    String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
