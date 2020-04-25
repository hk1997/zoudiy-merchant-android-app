package com.example.zoudiy.Activities.trip;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zoudiy.Models.ListCityResponse;
import com.example.zoudiy.Models.ListSchoolResponse;
import com.example.zoudiy.utils.City;
import com.example.zoudiy.utils.RetrofitClient;
import com.example.zoudiy.utils.School;
import com.example.zoudiy.utils.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTripViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private Vehicle vehicle;
    private MutableLiveData<City> city;
    private MutableLiveData<School> school;
    private HashSet<String> regionList;
    private MutableLiveData<String> tripType;
    private String weeklySchedule; //binary string of 0's and 1's for weekly schedule


    private String capacity;
    //lists
    private MutableLiveData<ArrayList<City>> cityList;
    private MutableLiveData<ArrayList<School>> schoolList;
    private String[] tripTypeList;
    private ArrayList<String> weekList;

    public AddTripViewModel() {
        cityList = new MutableLiveData<>();
        city = new MutableLiveData<>();
        regionList = new HashSet<>();
        schoolList = new MutableLiveData<>();
        school = new MutableLiveData<>();
        tripTypeList = new String[]{"Door to Door", "Stop Based"};
        tripType = new MutableLiveData<>();
        capacity = "";
        weeklySchedule = "";
        String[] temp = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        weekList = new ArrayList<>(Arrays.asList(temp));
    }

    public String getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(String weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public ArrayList<String> getWeekList() {
        return weekList;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public MutableLiveData<School> getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school.setValue(school);
    }

    public String[] getTripTypeList() {
        return tripTypeList;
    }

    public String getTripTypeFromList(int pos) {
        if (pos < 0 || pos >= tripTypeList.length)
            return "";
        return tripTypeList[pos];
    }

    public MutableLiveData<String> getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType.setValue(tripType);
    }

    public MutableLiveData<City> getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city.setValue(city);
    }

    public MutableLiveData<ArrayList<City>> getCityList() {
        if (cityList.getValue() == null) {
            cityList.setValue(new ArrayList<>());
            handleListCity();
        }
        return cityList;
    }

    public void setCityList(MutableLiveData<ArrayList<City>> cityList) {
        this.cityList = cityList;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public City getCityFromList(int pos) {
        if (cityList.getValue() == null || pos < 0 || pos >= cityList.getValue().size())
            return null;
        return cityList.getValue().get(pos);
    }

    public MutableLiveData<ArrayList<School>> getSchoolList() {
        if (schoolList.getValue() == null) {
            schoolList.setValue(new ArrayList<>());
        }
        return schoolList;
    }

    public void setSchoolList(ArrayList<School> schoolList) {
        this.schoolList.setValue(schoolList);
    }

    public School getSchoolFromList(int pos) {
        if (schoolList.getValue() == null || pos < 0 || pos >= schoolList.getValue().size())
            return null;
        return schoolList.getValue().get(pos);
    }

    public HashSet<String> getRegionList() {
        return regionList;
    }

    /**
     * Function to handle api request to load list of cities
     */
    private void handleListCity() {
        Call<ListCityResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .listCities();

        call.enqueue(new Callback<ListCityResponse>() {
            @Override
            public void onResponse(Call<ListCityResponse> call, Response<ListCityResponse> response) {
                try {

                    if (response.body().getSuccess()) {
                        ArrayList<City> l = response.body().getData().getCityList();
                        cityList.setValue(l);
                    } else {
                        Log.d("error", response.body().getMessage());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ListCityResponse> call, Throwable t) {
                Log.d("Failure", t.toString());
                //Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void handleListSchool(String cityId) {
        Call<ListSchoolResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .listSchool(cityId);

        call.enqueue(new Callback<ListSchoolResponse>() {
            @Override
            public void onResponse(Call<ListSchoolResponse> call, Response<ListSchoolResponse> response) {
                try {

                    if (response.body().getSuccess()) {
                        ArrayList<School> l = response.body().getData().getSchoolList();
                        schoolList.setValue(l);
                    } else {
                        Log.d("error", response.body().getMessage());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ListSchoolResponse> call, Throwable t) {
                Log.d("Failure", t.toString());
                //Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
