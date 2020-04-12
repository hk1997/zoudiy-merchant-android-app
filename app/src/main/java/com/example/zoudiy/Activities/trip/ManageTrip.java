package com.example.zoudiy.Activities.trip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.zoudiy.Activities.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ManageTrip extends Fragment {

    private ManageTripViewModel mViewModel;
    private FloatingActionButton buttonAddTrip;

    public static ManageTrip newInstance() {
        return new ManageTrip();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.manage_trip_fragment, container, false);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ManageTripViewModel.class);
        // TODO: Use the ViewModel
    }

}
