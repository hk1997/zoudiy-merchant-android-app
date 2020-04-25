package com.example.zoudiy.Models;

import com.example.zoudiy.utils.City;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListCityResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ListCityData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListCityData getData() {
        return data;
    }

    public void setData(ListCityData data) {
        this.data = data;
    }

    public class ListCityData {
        @SerializedName("cities")
        @Expose
        private ArrayList<City> cityList;

        public ListCityData(ArrayList<City> cityList) {
            this.cityList = cityList;
        }

        public ArrayList<City> getCityList() {
            return cityList;
        }

        public void setCityList(ArrayList<City> cityList) {
            this.cityList = cityList;
        }
    }
}
