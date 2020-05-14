package com.example.zoudiy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.utils.TripInfo;

import java.util.ArrayList;

public class TripInfoAdapter extends RecyclerView.Adapter<TripInfoAdapter.TripInfoViewHolder> {
    private ArrayList<TripInfo> tripInfoList;
    private Context mContext;
    private OnItemClick onItemClick;

    public TripInfoAdapter(ArrayList<TripInfo> tripInfoList, Context mContext, OnItemClick onItemClick) {
        this.tripInfoList = tripInfoList;
        this.mContext = mContext;
        this.onItemClick = onItemClick;
    }

    public void setTripInfoList(ArrayList<TripInfo> tripInfoList) {
        this.tripInfoList = tripInfoList;
    }

    @NonNull
    @Override
    public TripInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.trip_info_card, parent, false);
        return new TripInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripInfoViewHolder holder, int position) {
        final TripInfo trip = tripInfoList.get(position);
        //setting data to the views here
        holder.setDest(trip.getDest().getFullAddress());
        holder.setVehicle(trip.getVehicle().getVehicleNo() + " [" + trip.getVehicle().getType() + "]");
        Integer capacity = new Integer(trip.getCapacity());
        holder.setCapacity(capacity.toString());
        int availableCapacity = capacity - (trip.getKids() == null ? 0 : trip.getKids().size());
//        int availableCapacity = (int)capacity - trip.getKids().length;
        holder.setAvailableCapacity(Integer.toString(availableCapacity));
    }

    @Override
    public int getItemCount() {
        return tripInfoList.size();
    }


    public interface OnItemClick {
        void navigateToDetail(int pos);
    }

    public class TripInfoViewHolder extends RecyclerView.ViewHolder {

        private TextView dest, vehicle, capacity, availableCapacity;

        public TripInfoViewHolder(View itemView) {
            super(itemView);
            dest = itemView.findViewById(R.id.trip_info_dest);
            vehicle = itemView.findViewById(R.id.trip_info_vehicle);
            capacity = itemView.findViewById(R.id.trip_info_capacity);
            availableCapacity = itemView.findViewById(R.id.trip_info_available_capacity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.navigateToDetail(getAdapterPosition());
                }
            });
        }

        public void setDest(String dest) {
            this.dest.setText(dest);
        }

        public void setVehicle(String s) {
            this.vehicle.setText(s);
        }

        public void setCapacity(String s) {
            this.capacity.setText(s);
        }

        public void setAvailableCapacity(String s) {
            this.availableCapacity.setText(s);
        }
    }
}
