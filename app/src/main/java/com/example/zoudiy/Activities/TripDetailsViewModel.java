package com.example.zoudiy.Activities;

import androidx.lifecycle.ViewModel;

import com.example.zoudiy.utils.TripInfo;

import java.util.ArrayList;
import java.util.Arrays;

public class TripDetailsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private String token;
    private TripInfo tripInfo;
    private ArrayList<String> weekList;

    public TripDetailsViewModel() {
        String[] temp = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        this.weekList = new ArrayList<>(Arrays.asList(temp));
    }

    public ArrayList<String> getWeekList() {
        return weekList;
    }

    public TripInfo getTripInfo() {
        return tripInfo;
    }

    public void setTripInfo(TripInfo tripInfo) {
        this.tripInfo = tripInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        token = token.substring(1, token.length() - 1);
        this.token = token;
    }
}
