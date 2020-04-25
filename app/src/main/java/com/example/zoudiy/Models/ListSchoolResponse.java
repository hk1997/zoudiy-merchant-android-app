package com.example.zoudiy.Models;

import com.example.zoudiy.utils.School;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListSchoolResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ListSchoolData data;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ListSchoolData getData() {
        return data;
    }

    public class ListSchoolData {
        @SerializedName("schools")
        @Expose
        private ArrayList<School> schoolList;

        public ArrayList<School> getSchoolList() {
            return schoolList;
        }

        public void setSchoolList(ArrayList<School> schoolList) {
            this.schoolList = schoolList;
        }
    }
}
