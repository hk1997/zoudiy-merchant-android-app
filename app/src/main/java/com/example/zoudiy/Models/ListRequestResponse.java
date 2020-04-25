package com.example.zoudiy.Models;

import com.example.zoudiy.utils.ListRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListRequestResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ListResponseData data;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ListResponseData getData() {
        return data;
    }

    public class ListResponseData {
        @SerializedName("requests")
        @Expose
        ArrayList<ListRequest> requestList;

        public ArrayList<ListRequest> getRequestList() {
            return requestList;
        }

        public void setRequestList(ArrayList<ListRequest> requestList) {
            this.requestList = requestList;
        }
    }
}
