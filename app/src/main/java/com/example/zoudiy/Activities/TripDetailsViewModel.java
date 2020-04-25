package com.example.zoudiy.Activities;

import androidx.lifecycle.ViewModel;

public class TripDetailsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        token = token.substring(1, token.length() - 1);
        this.token = token;
    }
}
