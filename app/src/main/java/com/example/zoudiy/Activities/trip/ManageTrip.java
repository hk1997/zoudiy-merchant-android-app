package com.example.zoudiy.Activities.trip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.adapters.TripInfoAdapter;
import com.example.zoudiy.utils.Preference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ManageTrip extends Fragment {

    private ManageTripViewModel mViewModel;
    private FloatingActionButton buttonAddTrip;
    private TripInfoAdapter listAdapter;
    private RecyclerView recycler;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.manage_trip_fragment, container, false);

        if (mViewModel == null) {
            mViewModel = new ViewModelProvider(this).get(ManageTripViewModel.class);
            //setting access token for request handling
            mViewModel.setToken(Preference.getAccessToken(getContext()));
        }

        recycler = view.findViewById(R.id.manage_trip_fragment_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        listAdapter = new TripInfoAdapter(mViewModel.getTripList().getValue(), getContext());
        recycler.setAdapter(listAdapter);

        mViewModel.getTripList().observe(getViewLifecycleOwner(), vehicles -> {
            listAdapter.setTripInfoList(mViewModel.getTripList().getValue());
            listAdapter.notifyDataSetChanged();
        });


        buttonAddTrip = view.findViewById(R.id.button_add_trip);
        buttonAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "here", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_nav_trip_to_addTrip);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ManageTripViewModel.class);
        //setting access token for request handling
        mViewModel.setToken(Preference.getAccessToken(getContext()));
    }
}
