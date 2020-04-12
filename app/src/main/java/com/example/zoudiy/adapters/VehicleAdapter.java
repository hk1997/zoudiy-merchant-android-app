package com.example.zoudiy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.utils.Vehicle;

import java.util.ArrayList;


public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleHolder> {

    private ArrayList<Vehicle> vehicleList;
    private Context mContext;

    public VehicleAdapter(ArrayList<Vehicle> list, Context context) {
        this.vehicleList = list;
        this.mContext = context;
    }

    public void setVehicleList(ArrayList<Vehicle> l) {
        this.vehicleList = l;
    }


    @NonNull
    @Override
    public VehicleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.vehicle_info, parent, false);
        return new VehicleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleHolder holder, int position) {

        final Vehicle vehicle = vehicleList.get(position);
        //setting data to the views here
        holder.setVehicleNo(vehicle.getVehicleNo());
        holder.setVehicleType(vehicle.getType());
    }

    @Override
    public int getItemCount() {
        return vehicleList == null ? 0 : vehicleList.size();
    }

    public class VehicleHolder extends RecyclerView.ViewHolder {

        private TextView type, vehicleNo;

        public VehicleHolder(View itemView) {
            super(itemView);
            vehicleNo = itemView.findViewById(R.id.vehicle_info_vehicle_no);
            type = itemView.findViewById(R.id.vehicle_info_vehicle_type);
        }

        public void setVehicleType(String vehicleType) {
            type.setText(vehicleType);
        }

        public void setVehicleNo(String no) {
            vehicleNo.setText(no);
        }


    }
}
