package com.example.zoudiy.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListRequest {

    @SerializedName("_id")
    @Expose
    String requestId;

    @SerializedName("tripId")
    @Expose
    TripInfo trip;

    @SerializedName("merchantId")
    @Expose
    String merchantId;

    @SerializedName("kidId")
    @Expose
    Kid kid;

    @SerializedName("cost")
    @Expose
    String cost;

    @SerializedName("status")
    @Expose
    String status;

    public String getRequestId() {
        return requestId;
    }

    public TripInfo getTrip() {
        return trip;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public Kid getKid() {
        return kid;
    }

    public String getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }
}
