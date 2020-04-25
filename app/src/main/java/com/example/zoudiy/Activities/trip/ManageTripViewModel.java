package com.example.zoudiy.Activities.trip;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zoudiy.Models.TripInfoResponse;
import com.example.zoudiy.utils.RetrofitClient;
import com.example.zoudiy.utils.TripInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageTripViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    String token;
    private MutableLiveData<ArrayList<TripInfo>> tripList;

    public ManageTripViewModel() {
        token = "";
        tripList = new MutableLiveData<>();
    }

    public void setToken(String token) {
        token = token.substring(1, token.length() - 1);
        this.token = token;
    }

    public MutableLiveData<ArrayList<TripInfo>> getTripList() {
        if (tripList.getValue() == null) {
            tripList.setValue(new ArrayList<>());
            requestTripInfo();
        }
        return tripList;
    }

    public void setTripList(ArrayList<TripInfo> tripList) {
        this.tripList.setValue(tripList);
    }

    private void requestTripInfo() {

        Call<TripInfoResponse> call = RetrofitClient.getInstance().getApi().listTrip(token);

        call.enqueue(new Callback<TripInfoResponse>() {

            @Override
            public void onResponse(Call<TripInfoResponse> call, Response<TripInfoResponse> response) {

                if (response.body().getSuccess()) {
                    ArrayList<TripInfo> l = response.body().getData().getTripInfoList();
                    tripList.setValue(l);
                } else {
                    Log.d("failure from server", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<TripInfoResponse> call, Throwable t) {
                Log.d("failure from client", "Here");
            }
        });
    }
}
