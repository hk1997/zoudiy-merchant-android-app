package com.example.zoudiy.Activities.request;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zoudiy.utils.ListRequest;

import java.util.ArrayList;

public class ManageRequestViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> status;
    private String[] statusValues;
    private String token;
    private MutableLiveData<ArrayList<ListRequest>> requestList;
    private int selectedPosition;


    public ManageRequestViewModel() {
        status = new MutableLiveData<>();
        requestList = new MutableLiveData<>();
        status.setValue("");
        statusValues = new String[]{"Pending", "Accepted", "Rejected"};
        token = "";
        selectedPosition = 0;
    }

    public MutableLiveData<ArrayList<ListRequest>> getRequestList() {
        if (requestList.getValue() == null)
            requestList.setValue(new ArrayList<>());
        return requestList;
    }

    public void setRequestList(ArrayList<ListRequest> requestList) {
        this.requestList.setValue(requestList);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        token = token.substring(1, token.length() - 1);
        this.token = token;
    }

    public MutableLiveData<String> getStatus() {
        if (status.getValue().equals(""))
            status.setValue(statusValues[0]);
        return status;
    }

    public void setStatus(String status) {
        this.status.setValue(status);
    }

    public String[] getStatusValues() {
        return statusValues;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

}
