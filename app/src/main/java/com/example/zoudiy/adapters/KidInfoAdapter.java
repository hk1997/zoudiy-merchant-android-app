package com.example.zoudiy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.utils.Kid;

import java.util.ArrayList;

public class KidInfoAdapter extends RecyclerView.Adapter<KidInfoAdapter.KidInfoViewHolder> {
    private ArrayList<Kid> kidList;
    private Context mContext;
    private OnClick onClick;

    public KidInfoAdapter(ArrayList<Kid> kidList, Context mContext, OnClick onClick) {
        this.kidList = kidList;
        this.mContext = mContext;
        this.onClick = onClick;
    }

    public void setKidList(ArrayList<Kid> kidList) {
        this.kidList = kidList;
    }

    @NonNull
    @Override
    public KidInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.kid_info_card, parent, false);
        return new KidInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KidInfoViewHolder holder, int position) {
        final Kid kid = this.kidList.get(position);
        holder.setName(kid.getName());
        holder.setAddress(kid.getHomeAddress().getFullAddress());
    }

    @Override
    public int getItemCount() {
        return this.kidList.size();
    }

    public interface OnClick {
        void onMapClick(int pos);
    }

    public class KidInfoViewHolder extends RecyclerView.ViewHolder {
        private TextView name, address;
        private ImageView map;

        public KidInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.kid_info_name);
            address = itemView.findViewById(R.id.kid_info_address);
            map = itemView.findViewById(R.id.kid_info_map_button);
            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onMapClick(getAdapterPosition());
                }
            });
        }

        public void setName(String s) {
            this.name.setText(s);
        }

        public void setAddress(String s) {
            this.address.setText(s);
        }
    }
}
