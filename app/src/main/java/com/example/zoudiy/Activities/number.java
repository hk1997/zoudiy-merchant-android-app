package com.example.zoudiy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zoudiy.Activities.R;
import com.example.zoudiy.Activities.otp;
import com.example.zoudiy.Models.ApiResponse;
import com.example.zoudiy.utils.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class number extends AppCompatActivity {
    private EditText phoneNoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        phoneNoEditText= findViewById(R.id.edit_text_phone_no);

    }



    public void sendOtp(View v){
        if(phoneNoEditText==null)
            return;
        String regex = "\\d+";

        if(phoneNoEditText.getText().toString().length()==10 && phoneNoEditText.getText().toString().matches(regex)){

            final String phone = "+91 "+phoneNoEditText.getText().toString();
            Call<ApiResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .Sendotp(phone);
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    try {
                        Log.d("Number Activity","Request Successful");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.d("Failure",t.toString());
                    //Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            //loading new view while request is sent asynchonously
            Intent intent= new Intent(number.this, otp.class);
            intent.putExtra("phone", phone);
            startActivity(intent);

        }
        else{
            Toast.makeText(this,"Invalid mobile number",Toast.LENGTH_SHORT).show();
        }
    }
}
