package com.example.zoudiy.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zoudiy.utils.Preference;


public class TripDetails extends Fragment {

    private TripDetailsViewModel mViewModel;

    public static TripDetails newInstance() {
        return new TripDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mViewModel == null) {
            mViewModel = new ViewModelProvider(this).get(TripDetailsViewModel.class);
            mViewModel.setToken(Preference.getAccessToken(getContext()));
        }

        View view = inflater.inflate(R.layout.trip_details_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TripDetailsViewModel.class);
        mViewModel.setToken(Preference.getAccessToken(getContext()));
    }
}
