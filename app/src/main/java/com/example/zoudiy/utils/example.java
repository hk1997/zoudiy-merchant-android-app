package com.example.zoudiy.utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class example {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose



    private com.example.zoudiy.Models.Data data;

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

    public com.example.zoudiy.Models.Data getData() {
        return data;
    }

    public void setData(com.example.zoudiy.Models.Data data) {
        this.data = data;
    }

}
