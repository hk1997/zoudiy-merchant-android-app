package com.example.zoudiy.Activities.vehicle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.adapters.VehicleAdapter;
import com.example.zoudiy.utils.Preference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ManageVehicle extends Fragment {


    private FloatingActionButton buttonAddVehicle;
    private VehicleAdapter listAdapter;
    private RecyclerView recycler;
    private ManageVehicleViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manage_vehicle_fragment, container, false);
        //initializing view model
        mViewModel = new ViewModelProvider(this).get(ManageVehicleViewModel.class);
        //setting access token for request handling
        mViewModel.setToken(Preference.getAccessToken(getContext()));

        buttonAddVehicle = view.findViewById(R.id.button_add_vehicle);
        buttonAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_manageVehicle_to_addVehicle);
            }
        });

        recycler = view.findViewById(R.id.manage_vehcile_fragment_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        listAdapter = new VehicleAdapter(mViewModel.getVehicleList().getValue(), getContext());
        recycler.setAdapter(listAdapter);

        mViewModel.getVehicleList().observe(getViewLifecycleOwner(), vehicles -> {
            listAdapter.setVehicleList(mViewModel.getVehicleList().getValue());
            listAdapter.notifyDataSetChanged();
        });

//        listAdapter.notifyDataSetChanged();
        //listAdapter.getVehicleList(getContext());

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ManageVehicleViewModel.class);
        // TODO: Use the ViewModel
    }

}
