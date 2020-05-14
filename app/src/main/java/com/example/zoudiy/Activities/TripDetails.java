package com.example.zoudiy.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zoudiy.adapters.KidInfoAdapter;
import com.example.zoudiy.utils.Kid;
import com.example.zoudiy.utils.Preference;
import com.example.zoudiy.utils.TripInfo;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class TripDetails extends Fragment {

    private TripDetailsViewModel mViewModel;
    private TextView dest, startTime, tripType;
    private ChipGroup weeklySchedule, areasCovered;
    private TripInfo trip;
    private RecyclerView recycler;
    private KidInfoAdapter kidAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trip_details_fragment, container, false);
        if (mViewModel == null) {
            //loading this as part of activity scope for sharing with fragment
            mViewModel = new ViewModelProvider(getActivity()).get(TripDetailsViewModel.class);
            mViewModel.setToken(Preference.getAccessToken(getContext()));
        }
        //loading components
        dest = view.findViewById(R.id.trip_details_fragment_dest);
        startTime = view.findViewById(R.id.trip_details_fragment_start_time);
        tripType = view.findViewById(R.id.trip_details_fragment_trip_type);
        weeklySchedule = view.findViewById(R.id.trip_details_fragment_weekly_schedule);
        areasCovered = view.findViewById(R.id.trip_details_fragment_areas_covered);
        recycler = view.findViewById(R.id.trip_details_fragment_recycler);

        this.trip = mViewModel.getTripInfo();
        if (this.trip == null) {
            Snackbar.make(getView(), "Please select a trip", BaseTransientBottomBar.LENGTH_SHORT).show();
            return view;
        }

        setLayoutDetails();

        return view;
    }

    /**
     * Function to set layout details from trip object
     */
    private void setLayoutDetails() {
        dest.setText(trip.getDest().getFullAddress());
        startTime.setText(trip.getStartTime());
        tripType.setText(trip.getTripType());
        String weeklySchedule = trip.getWeeklySchedule();
        //setting weekly chips
        for (int i = 0; i < weeklySchedule.length(); i++) {
            if (weeklySchedule.charAt(i) == '1') {
                Chip c = new Chip(getContext());
                c.setText(mViewModel.getWeekList().get(i));
                this.weeklySchedule.addView(c);
            }
        }
        //setting areas covered chips
        for (String s : trip.getAreasCovered()) {
            Chip c = new Chip(getContext());
            c.setText(s);
            this.areasCovered.addView(c);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        kidAdapter = new KidInfoAdapter(this.trip.getKids(), getContext(), new KidInfoAdapter.OnClick() {
            @Override
            public void onMapClick(int pos) {
                Kid kid = trip.getKids().get(pos);

                String uri = "geo:" +
                        kid.getHomeAddress().getCoordinates().getLat() + "," + kid.getHomeAddress().getCoordinates().getLng();
                Uri gmmIntentUri = Uri.parse(uri);
                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");
                // Attempt to start an activity that can handle the Intent
                getContext().startActivity(mapIntent);
            }
        });
        recycler.setAdapter(kidAdapter);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //loading this as part of activity scope for sharing with fragment
        mViewModel = new ViewModelProvider(getActivity()).get(TripDetailsViewModel.class);
        mViewModel.setToken(Preference.getAccessToken(getContext()));
    }
}
