package com.example.zoudiy.Activities.vehicle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddVehicleViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private String[] items;
    private String hintDropdown;
    private MutableLiveData<String> vehicleNo, vehicleType;


    public AddVehicleViewModel() {
        items = new String[]{"Auto", "Van", "Bus"};
        hintDropdown = "Vehicle Type";
        vehicleNo = new MutableLiveData<>("");
        vehicleType = new MutableLiveData<>("");
    }

    public String getVehicleNo() {
        return vehicleNo.getValue();
    }

    public void setVehicleNo(String s) {
        vehicleNo.setValue(s);
    }

    public String getVehicleType() {
        return vehicleType.getValue();
    }

    public void setVehicleType(String s) {
        vehicleType.setValue(s.toLowerCase());
    }

    public void setVehicleType(int pos) {
        vehicleType.setValue(items[pos].toLowerCase());
    }

    public String getHint() {
        return hintDropdown;
    }

    public String[] getItems() {
        return items;
    }


}
