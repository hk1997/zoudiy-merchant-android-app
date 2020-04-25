package com.example.zoudiy.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripAddress {
    @SerializedName("fullAddress")
    @Expose
    String fullAddress;

    @SerializedName("_id")
    @Expose
    String addressId;

    @SerializedName("coordinates")
    @Expose
    Coordinates coordinates;

    @SerializedName("city")
    @Expose
    String city;

    @SerializedName("landmark")
    @Expose
    String landmark;

    public String getFullAddress() {
        return fullAddress;
    }

    public String getAddressId() {
        return addressId;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCity() {
        return city;
    }

    public String getLandmark() {
        return landmark;
    }
}
