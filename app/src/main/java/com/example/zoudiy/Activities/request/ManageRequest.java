package com.example.zoudiy.Activities.request;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.Models.ApiResponse;
import com.example.zoudiy.Models.ListRequestResponse;
import com.example.zoudiy.adapters.ListRequestAdapter;
import com.example.zoudiy.utils.ListRequest;
import com.example.zoudiy.utils.Preference;
import com.example.zoudiy.utils.RetrofitClient;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManageRequest extends Fragment {

    private ManageRequestViewModel mViewModel;
    private AutoCompleteTextView editTextFilledExposedDropdown;
    private TextInputLayout txt;
    private RecyclerView recycler;
    private ListRequestAdapter listRequestAdapter;
    private MaterialAlertDialogBuilder mAlert;

    public static ManageRequest newInstance() {
        return new ManageRequest();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.manage_request_fragment, container, false);

        if (mViewModel == null) {
            mViewModel = new ViewModelProvider(this).get(ManageRequestViewModel.class);
            mViewModel.setToken(Preference.getAccessToken(getContext()));
        }

        editTextFilledExposedDropdown =
                view.findViewById(R.id.filled_exposed_dropdown);
        txt = view.findViewById(R.id.dropdown_menu_text_input_layout);
        setStatusDropdown();


        recycler = view.findViewById(R.id.manage_request_fragment_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);

        mAlert = new MaterialAlertDialogBuilder(getContext());

        listRequestAdapter = new ListRequestAdapter(mViewModel.getRequestList().getValue(), getContext(), new ListRequestAdapter.ResponseClickListener() {
            @Override
            public void onAccept(int pos) {
                mAlert.setTitle("Confirm response");
                mAlert.setMessage("Are you sure you want to accept request");
                mAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        dialog.dismiss();
                    }
                });
                mAlert.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        handleOnAccept(mViewModel.getRequestList().getValue().get(pos), true);
                        dialog.dismiss();
                    }
                });
                mAlert.show();
            }

            @Override
            public void onReject(int pos) {
                mAlert.setTitle("Confirm response");
                mAlert.setMessage("Are you sure you want to reject request");
                mAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        dialog.dismiss();
                    }
                });
                mAlert.setPositiveButton("Reject", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        handleOnAccept(mViewModel.getRequestList().getValue().get(pos), false);
                        dialog.dismiss();
                    }
                });
                mAlert.show();
            }
        });
        recycler.setAdapter(listRequestAdapter);

        mViewModel.getRequestList().observe(getViewLifecycleOwner(), req -> {
            listRequestAdapter.setListRequest(mViewModel.getRequestList().getValue());
            listRequestAdapter.notifyDataSetChanged();
        });

        Log.d("token", mViewModel.getToken());
        return view;
    }

    private void handleGetListRequest() {
        ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                "Please wait while we load your requests", true);
        Call<ListRequestResponse> call = RetrofitClient.getInstance().getApi()
                .listRequest(mViewModel.getToken(), mViewModel.getStatus().getValue().toLowerCase());
        call.enqueue(new Callback<ListRequestResponse>() {
            @Override
            public void onResponse(Call<ListRequestResponse> call, Response<ListRequestResponse> response) {
                dialog.hide();
                if (response.body().getSuccess()) {
                    mViewModel.setRequestList(response.body().getData().getRequestList());
                } else {
                    Snackbar.make(getView(), response.body().getMessage(), BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListRequestResponse> call, Throwable t) {
                dialog.hide();
                Snackbar.make(getView(), "Some error occured! Please try after some time.", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
    }

    private void setStatusDropdown() {
        txt.setHint("Select Status");
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        getContext(),
                        R.layout.dropdown_menu_popup_item,
                        mViewModel.getStatusValues());
        editTextFilledExposedDropdown.setAdapter(adapter);
        editTextFilledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mViewModel.setSelectedPosition(position);
                mViewModel.setStatus(mViewModel.getStatusValues()[position]);
                handleGetListRequest();
            }
        });
        editTextFilledExposedDropdown.setSelection(mViewModel.getSelectedPosition());
        handleGetListRequest();
    }

    private void handleOnAccept(ListRequest l, boolean accept) {
        Call<ApiResponse> call = RetrofitClient.getInstance()
                .getApi()
                .serveRequest(mViewModel.getToken(), accept, l.getRequestId());
        ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                "Please wait while we process your request", true);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().getSuccess()) {
                    dialog.hide();
                    Snackbar.make(getView(), "Request  successfull", BaseTransientBottomBar.LENGTH_SHORT).show();
                    handleGetListRequest();
                } else {
                    dialog.hide();
                    Snackbar.make(getView(), "Some error occured, Please try after some time", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                dialog.hide();
                Snackbar.make(getView(), "Some error occured, Please try after some time", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ManageRequestViewModel.class);
        mViewModel.setToken(Preference.getAccessToken(getContext()));
    }
}
