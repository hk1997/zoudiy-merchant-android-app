package com.example.zoudiy.utils;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class School {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("address")
    @Expose
    String addressId;
    @SerializedName("cityId")
    @Expose
    String cityId;
    @SerializedName("_id")
    @Expose
    String schoolId;
    @SerializedName("branch")
    @Expose
    String branch;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " [" + branch + "]";
    }
}
