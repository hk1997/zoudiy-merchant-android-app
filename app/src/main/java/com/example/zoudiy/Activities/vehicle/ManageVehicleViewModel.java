package com.example.zoudiy.Activities.vehicle;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zoudiy.Models.VehicleResponse;
import com.example.zoudiy.utils.RetrofitClient;
import com.example.zoudiy.utils.Vehicle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageVehicleViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<Vehicle>> vehicleList;
    private String token;

    public ManageVehicleViewModel() {
        token = "";
        vehicleList = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Vehicle>> getVehicleList() {

        if (vehicleList.getValue() == null) {
            vehicleList.setValue(new ArrayList<>());
            handleRequest();
        }
        return vehicleList;
    }

    public void setToken(String token) {
        token = token.substring(1, token.length() - 1);
        this.token = token;
    }

    /**
     * Function that returns item at position pos in vehicle list
     *
     * @param pos
     * @return
     */
    public Vehicle getVehicle(int pos) {
        return this.getVehicleList().getValue().get(pos);
    }

    /**
     * Function calls api to retrieve  vehicle list
     */
    public void handleRequest() {
        Call<VehicleResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getVehicle(token);

        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                try {
                    Log.d("yo", "in manage vehicle model " + response.body().getSuccess());
                    if (response.body().getSuccess()) {
                        ArrayList<Vehicle> l = response.body().getData().getVehicleList();
                        Log.d("vehicle", "vehicle size" + l.size());
                        getVehicleList().setValue(l);

                    } else {
                        Log.d("error", response.body().getMessage());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                Log.d("Failure", t.toString());
                //Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
