package com.example.zoudiy.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kid {

    @SerializedName("_id")
    @Expose
    String kidId;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("dob")
    @Expose
    String dob;

    @SerializedName("school")
    @Expose
    String schoolId;

    @SerializedName("home")
    @Expose
    TripAddress homeAddress;

    public String getKidId() {
        return kidId;
    }

    public void setKidId(String kidId) {
        this.kidId = kidId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public TripAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(TripAddress homeAddress) {
        this.homeAddress = homeAddress;
    }
}
