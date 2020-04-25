package com.example.zoudiy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.utils.Kid;
import com.example.zoudiy.utils.ListRequest;

import java.util.ArrayList;

public class ListRequestAdapter extends RecyclerView.Adapter<ListRequestAdapter.ListRequestViewHolder> {

    ArrayList<ListRequest> listRequest;
    Context mContext;
    ResponseClickListener clickListener;


    public ListRequestAdapter(ArrayList<ListRequest> listRequest, Context mContext, ResponseClickListener clickListener) {
        this.listRequest = listRequest;
        this.mContext = mContext;
        this.clickListener = clickListener;
    }

    public void setListRequest(ArrayList<ListRequest> listRequest) {
        this.listRequest = listRequest;
    }

    @NonNull
    @Override
    public ListRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_request_card, parent, false);
        return new ListRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRequestViewHolder holder, int position) {
        final ListRequest request = listRequest.get(position);
        holder.setTripDest(request.getTrip().getDest().getFullAddress());
        holder.setTripStartTime("Start Time: " + request.getTrip().getStartTime());
        Integer availableCapacity = new Integer(request.getTrip().getCapacity());
        ArrayList<Kid> kids = request.getTrip().getKids();
        availableCapacity = availableCapacity - ((kids == null) ? 0 : kids.size());
        holder.setTripAvailableCapacity("Available Capacity: " + availableCapacity.toString());
        holder.setDestUser(request.getKid().getHomeAddress().getFullAddress());
        holder.setKidName(request.getKid().getName());
        if (!request.getStatus().equals("pending")) {
            holder.hideAcceptButton();
            holder.hideRejectButton();
        } else {
            holder.showAcceptButton();
            holder.showRejectButton();
        }
    }

    @Override
    public int getItemCount() {
        return listRequest.size();
    }

    public interface ResponseClickListener {
        void onAccept(int pos);

        void onReject(int pos);
    }

    public class ListRequestViewHolder extends RecyclerView.ViewHolder {

        private TextView tripDest, tripStartTime, tripAvailableCapacity, destUser, kidName;
        private Button accept, reject;

        public ListRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            tripDest = itemView.findViewById(R.id.request_info_dest);
            tripStartTime = itemView.findViewById(R.id.request_info_start_time);
            tripAvailableCapacity = itemView.findViewById(R.id.request_info_available_capacity);
            destUser = itemView.findViewById(R.id.request_info_kid_address);
            kidName = itemView.findViewById(R.id.request_info_kid_name);
            accept = itemView.findViewById(R.id.request_info_button_accept);
            reject = itemView.findViewById(R.id.request_info_button_reject);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onAccept(getAdapterPosition());
                }
            });

            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onReject(getAdapterPosition());
                }
            });

        }

        public void setTripDest(String s) {
            this.tripDest.setText(s);
        }

        public void setTripStartTime(String s) {
            this.tripStartTime.setText(s);
        }

        public void setTripAvailableCapacity(String s) {
            this.tripAvailableCapacity.setText(s);
        }

        public void setDestUser(String s) {
            this.destUser.setText(s);
        }

        public void setKidName(String s) {
            this.kidName.setText(s);
        }

        public void hideAcceptButton() {
            accept.setVisibility(View.INVISIBLE);
        }

        public void hideRejectButton() {
            reject.setVisibility(View.INVISIBLE);
        }

        public void showAcceptButton() {
            accept.setVisibility(View.VISIBLE);
        }

        public void showRejectButton() {
            reject.setVisibility(View.VISIBLE);
        }
    }
}
