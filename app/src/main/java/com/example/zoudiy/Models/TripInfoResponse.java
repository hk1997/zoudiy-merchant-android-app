package com.example.zoudiy.Models;

import com.example.zoudiy.utils.TripInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TripInfoResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataTripInfo data;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public DataTripInfo getData() {
        return data;
    }

    public class DataTripInfo {
        @SerializedName("trips")
        @Expose
        private ArrayList<TripInfo> tripInfoList;

        public ArrayList<TripInfo> getTripInfoList() {
            return tripInfoList;
        }

        public void setTripInfoList(ArrayList<TripInfo> tripInfoList) {
            this.tripInfoList = tripInfoList;
        }
    }
}
