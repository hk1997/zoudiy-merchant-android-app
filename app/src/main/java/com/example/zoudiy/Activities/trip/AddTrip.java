package com.example.zoudiy.Activities.trip;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.Activities.vehicle.ManageVehicleViewModel;
import com.example.zoudiy.Models.ApiResponse;
import com.example.zoudiy.utils.AddTripClass;
import com.example.zoudiy.utils.City;
import com.example.zoudiy.utils.Preference;
import com.example.zoudiy.utils.RetrofitClient;
import com.example.zoudiy.utils.School;
import com.example.zoudiy.utils.Vehicle;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddTrip extends Fragment {

    private AddTripViewModel mViewModel;
    private ManageVehicleViewModel vehicleViewModel;
    private AutoCompleteTextView editTextFilledExposedDropdown;
    private AutoCompleteTextView editTextFilledExposedDropdownCity;
    private AutoCompleteTextView editTextFilledExposedDropdownSchool;
    private AutoCompleteTextView editTextFilledExposedDropdownTripType;

    private TextInputLayout txt;
    private TextInputLayout txtCity;
    private TextInputLayout txtSchool;
    private TextInputLayout txtTripType;
    private TextInputLayout txtTripCapacityLayout;

    private TextInputEditText txtTripCapacity;

    private ArrayAdapter<Vehicle> adapter;
    private ArrayAdapter<City> adapterCity;
    private ArrayAdapter<School> adapterSchool;
    private ArrayAdapter<String> adpaterTripType;
    private ChipGroup cityRegions;
    private ChipGroup weeklySchedule;
    private TimePicker timePicker;

    private Button addTripButton;

    public static AddTrip newInstance() {
        return new AddTrip();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_trip_fragment, container, false);

        if (mViewModel == null || vehicleViewModel == null) {
            mViewModel = new ViewModelProvider(this).get(AddTripViewModel.class);
            vehicleViewModel = new ViewModelProvider(this).get(ManageVehicleViewModel.class);
            //setting access token to model
            vehicleViewModel.setToken(Preference.getAccessToken(getContext()));
        }
        // code for dropdown section
        editTextFilledExposedDropdown =
                view.findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdownCity = view.findViewById(R.id.filled_exposed_dropdown2);
        editTextFilledExposedDropdownSchool = view.findViewById(R.id.filled_exposed_dropdown3);
        editTextFilledExposedDropdownTripType = view.findViewById(R.id.filled_exposed_dropdown4);

        cityRegions = view.findViewById(R.id.add_trip_fragment_city_region_chip_group);
        weeklySchedule = view.findViewById(R.id.add_trip_fragment_weekly_schedule);
        timePicker = view.findViewById(R.id.simpleTimePicker);

        txt = view.findViewById(R.id.dropdown_menu_text_input_layout);
        txtCity = view.findViewById(R.id.dropdown_menu_text_input_layout2);
        txtSchool = view.findViewById(R.id.dropdown_menu_text_input_layout3);
        txtTripType = view.findViewById(R.id.dropdown_menu_text_input_layout4);
        txtTripCapacityLayout = view.findViewById(R.id.text_input_layout);
        txtTripCapacity = view.findViewById(R.id.text_input_text);
        //setting hints
        txt.setHint("Vehicle");
        txtCity.setHint("City");
        txtSchool.setHint("School");
        txtTripType.setHint("Trip Type");
        txtTripCapacityLayout.setHint("Vehicle Capacity");
        txtTripCapacity.setInputType(InputType.TYPE_CLASS_NUMBER);

        addTripButton = view.findViewById(R.id.add_trip_fragment_add_button);

        //setting vehicle list
        setVehicleList();
        setCityList();
        setCityRegionsAndSchool();
        setWeeklySchedule();

        handleCapacityChange();

        vehicleViewModel.getVehicleList().observe(getViewLifecycleOwner(), vehicles -> {
            setVehicleList();
        });

        mViewModel.getCity().observe(getViewLifecycleOwner(), city -> {
            //updating school list on city change
            setCityRegionsAndSchool();
        });

        //for school dropdown
        mViewModel.getSchoolList().observe(getViewLifecycleOwner(), schools -> {
            setSchoolList();
        });

        setTripTypeList();

        addTripButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {
                    handleAddTrip(v);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void handleAddTrip(View v) throws JSONException {
        //validation checks

        Vehicle vehicle = mViewModel.getVehicle();
        if (vehicle == null) {
            Snackbar.make(getView(), "Select Vehicle", BaseTransientBottomBar.LENGTH_SHORT).show();
            return;
        }
        City city = mViewModel.getCity().getValue();
        if (city == null) {
            Snackbar.make(getView(), "Select City", BaseTransientBottomBar.LENGTH_SHORT).show();
            return;
        }
        School school = mViewModel.getSchool().getValue();
        if (school == null) {
            Snackbar.make(getView(), "Select School", BaseTransientBottomBar.LENGTH_SHORT).show();
            return;
        }
        String capacity = mViewModel.getCapacity();
        if (capacity == null || capacity.equals("") || capacity.equals("0")) {
            Snackbar.make(getView(), "Provide Vehicle Capacity", BaseTransientBottomBar.LENGTH_SHORT).show();
            return;
        }

        String startTime = timePicker.getHour() + "-" + timePicker.getMinute();
        Log.d("time", "time-" + startTime);
        String tripType = mViewModel.getTripType().getValue();
        if (tripType == null) {
            Snackbar.make(getView(), "Select trip type", BaseTransientBottomBar.LENGTH_SHORT).show();
            return;
        }

        String weeklyScheduleBinaryString = getScheduleBinaryString();
        if (weeklyScheduleBinaryString.equals("0000000")) {
            Snackbar.make(getView(), "Select Weekly Schedule", BaseTransientBottomBar.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> selectedCityRegions = getCityAreasMap();
        if (selectedCityRegions.size() == 0) {
            Snackbar.make(getView(), "Select Regions", BaseTransientBottomBar.LENGTH_SHORT).show();
            return;
        }
        sendRequestAddTrip(vehicle, city, school, capacity, startTime, tripType,
                weeklyScheduleBinaryString, selectedCityRegions, v);
    }

    private void sendRequestAddTrip(Vehicle vehicle, City city,
                                    School school, String capacity, String startTime,
                                    String tripType, String weeklyScheduleBinaryString,
                                    ArrayList<String> selectedCityRegions, View v) {

        String token = Preference.getAccessToken(getContext());
        token = token.substring(1, token.length() - 1);

        String[] cityRegions = selectedCityRegions.toArray(new String[selectedCityRegions.size()]);

        AddTripClass ob = new AddTripClass(vehicle.get_id(), city.getId(), school.getAddressId(),
                weeklyScheduleBinaryString, cityRegions, tripType, capacity, startTime, token);


        Call<ApiResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .addTrip(ob);

        //starting progress indicator
        ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                "Please wait while we add your trip", true);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {

                    if (response.body().getSuccess()) {
                        dialog.hide();
                        Snackbar.make(v, "Successfully Added Trip", BaseTransientBottomBar.LENGTH_SHORT).show();
                        Navigation.findNavController(v).navigate(R.id.action_addTrip_to_nav_trip);
                    } else {
                        dialog.hide();
                        Snackbar.make(v, response.body().getMessage(), BaseTransientBottomBar.LENGTH_SHORT).show();
                        Log.d("error", response.body().getMessage());
                    }

                } catch (Exception e) {
                    dialog.hide();
                    Snackbar.make(v, "Exception occured", BaseTransientBottomBar.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Failure", t.toString());
                dialog.hide();
                Snackbar.make(v, "Failed", BaseTransientBottomBar.LENGTH_SHORT).show();

                //Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private ArrayList<String> getCityAreasMap() throws JSONException {
        ArrayList<String> l = new ArrayList<>();
        int chipsCount = cityRegions.getChildCount();
        if (chipsCount != 0) {
            int i = 0;
            while (i < chipsCount) {
                Chip chip = (Chip) cityRegions.getChildAt(i);
                if (chip.isChecked()) {
                    l.add(chip.getText().toString());
                }
                i++;
            }

        }
        return l;
    }

    private String getScheduleBinaryString() {
        char[] a = new char[7];
        Arrays.fill(a, '0');
        int chipsCount = weeklySchedule.getChildCount();
        if (chipsCount == 0) {
            new String(a);
        } else {
            int i = 0;
            while (i < chipsCount) {
                Chip chip = (Chip) weeklySchedule.getChildAt(i);
                if (chip.isChecked()) {
                    a[i] = '1';
                }
                i++;
            }
        }

        return new String(a);
    }

    private void setWeeklySchedule() {
        ArrayList<String> weekList = mViewModel.getWeekList();
        for (String s : weekList) {
            Chip c = new Chip(getContext());
            c.setText(s);
            c.setCheckable(true);
            weeklySchedule.addView(c);
        }
    }

    private void handleCapacityChange() {
        txtTripCapacity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.setCapacity(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setTripTypeList() {
        ArrayList<String> l = new ArrayList<>(Arrays.asList(mViewModel.getTripTypeList()));
        adpaterTripType =
                new ArrayAdapter<>(
                        getContext(),
                        R.layout.dropdown_menu_popup_item,
                        l);
        editTextFilledExposedDropdownTripType.setAdapter(adpaterTripType);
        editTextFilledExposedDropdownTripType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.setTripType(mViewModel.getTripTypeFromList(position));
            }
        });
    }

    private void setVehicleList() {
        adapter =
                new ArrayAdapter<>(
                        getContext(),
                        R.layout.dropdown_menu_popup_item,
                        vehicleViewModel.getVehicleList().getValue());
        editTextFilledExposedDropdown.setAdapter(adapter);
        editTextFilledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.setVehicle(vehicleViewModel.getVehicle(position));
            }
        });
    }

    private void setCityList() {
        adapterCity = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, mViewModel.getCityList().getValue());
        mViewModel.getCityList().observe(getViewLifecycleOwner(), cities -> {
            adapterCity = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, mViewModel.getCityList().getValue());
            editTextFilledExposedDropdownCity.setAdapter(adapterCity);
            editTextFilledExposedDropdownCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mViewModel.setCity(mViewModel.getCityFromList(position));
                }
            });
        });
    }

    private void setSchoolList() {
        adapterSchool =
                new ArrayAdapter<>(
                        getContext(),
                        R.layout.dropdown_menu_popup_item,
                        mViewModel.getSchoolList().getValue());
        editTextFilledExposedDropdownSchool.setAdapter(adapterSchool);
        editTextFilledExposedDropdownSchool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.setSchool(mViewModel.getSchoolFromList(position));
            }
        });

    }

    private void setCityRegionsAndSchool() {
        City c = mViewModel.getCity().getValue();
        if (c == null)
            return;
        //updating school list based on change in city
        mViewModel.setSchoolList(new ArrayList<>());
        mViewModel.handleListSchool(c.getId());
        cityRegions.removeAllViews();
        if (c != null) {
            String[] regions = c.getRegions();
            for (String s : regions) {
                Chip chip = new Chip(getContext());
                chip.setText(s);
                chip.setCheckable(true);
                cityRegions.addView(chip);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddTripViewModel.class);
        vehicleViewModel = new ViewModelProvider(this).get(ManageVehicleViewModel.class);
        //setting access token to model
        vehicleViewModel.setToken(Preference.getAccessToken(getContext()));
    }

}
