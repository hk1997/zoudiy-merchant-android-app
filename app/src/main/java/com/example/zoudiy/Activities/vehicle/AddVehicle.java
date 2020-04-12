package com.example.zoudiy.Activities.vehicle;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.Models.ApiResponse;
import com.example.zoudiy.utils.Preference;
import com.example.zoudiy.utils.RetrofitClient;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddVehicle extends Fragment {

    Button addVehicleButton;
    private AddVehicleViewModel mViewModel;
    private AutoCompleteTextView editTextFilledExposedDropdown;
    private TextInputLayout txt;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_vehicle_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(AddVehicleViewModel.class);

        // code for dropdown section
        editTextFilledExposedDropdown =
                view.findViewById(R.id.filled_exposed_dropdown);

        txt = view.findViewById(R.id.dropdown_menu_text_input_layout);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        getContext(),
                        R.layout.dropdown_menu_popup_item,
                        mViewModel.getItems());

        txt.setHint(mViewModel.getHint());
        editTextFilledExposedDropdown.setAdapter(adapter);
        editTextFilledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.setVehicleType(position);
            }
        });

        addVehicleButton = view.findViewById(R.id.button_add_vehicle_addVehicleFragment);
        addVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddVehicle(v);
            }
        });

        //dynamically updating vehicle no
        TextInputEditText vehicleNoEditText = view.findViewById(R.id.edit_text_vehicle_no_addVehicleFragment);
        vehicleNoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.setVehicleNo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }


    /**
     * Function to handle request
     */
    public void handleRequest(View view, String vehicleNo, String type) {
        String token = Preference.getAccessToken(getActivity());
        token = token.substring(1, token.length() - 1);
        Call<ApiResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .addVehicle(vehicleNo, type, token);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    ApiResponse body = response.body();
                    Snackbar.make(view, response.body().getMessage(), BaseTransientBottomBar.LENGTH_SHORT).show();
                    Log.d("da", "bitch got no chill");
                    if (response.body().getSuccess()) {
                        // redirecting back to manage vehicle activity
                        Log.d("yo", "yo bitches");
                        Navigation.findNavController(view).navigate(R.id.action_addVehicle_to_manageVehicle);
                    } else {
                        Log.e("Request error", response.body().getMessage());
                        addVehicleButton.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Failure", t.toString());
                //Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * Function to handle when user clicks on add vehicle button
     */
    public void handleAddVehicle(View view) {
        String vehicleNo = mViewModel.getVehicleNo();
        String vehicleType = mViewModel.getVehicleType();
        if (vehicleNo.equals("")) {
            Snackbar.make(view, "Vehicle Number required", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (vehicleType.equals("")) {
            Snackbar.make(view, "Vehicle Type required", Snackbar.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getActivity(), "Saving Vehicle", Toast.LENGTH_SHORT).show();
        addVehicleButton.setVisibility(View.INVISIBLE);
        handleRequest(view, vehicleNo, vehicleType);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddVehicleViewModel.class);
        // TODO: Use the ViewModel
    }

}
