package com.example.zoudiy.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zoudiy.utils.Preference;
import com.example.zoudiy.utils.RetrofitClient;
import com.example.zoudiy.Models.ApiResponse;
import com.goodiebag.pinview.Pinview;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class otp extends AppCompatActivity {

    EditText otpEditText;
    TextView textView;
    TextView otpTimer;
    String phone;
    String textViewText;
    Button resendOtpButton;
    Button verifyOtpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otpEditText = findViewById(R.id.edit_text_otp);
        textView = findViewById(R.id.text_view_phone_text);
        otpTimer = findViewById(R.id.text_vew_timer_otp);
        resendOtpButton = findViewById(R.id.button_resend_otp);
        verifyOtpButton = findViewById(R.id.button_verify_otp);
        phone = getIntent().getExtras().getString("phone");

        if(textView!=null)
            textViewText = "OTP sent successfully to "+phone+". Please verify to proceed.";
        textView.setText(textViewText);
//        pin6 = (Pinview) findViewById(R.id.pinview);
//        pin6.setTextColor(Color.parseColor("#1E645F"));
//        pin6.setPinViewEventListener(new Pinview.PinViewEventListener() {
//            @Override
//            public void onDataEntered(Pinview pinview, boolean fromUser) {
//                getotp= pinview.getValue();
//                if( getotp.length() == 6 )
//                {
//                    mobileNo =getIntent().getStringExtra("Mobile_no");
//                    Call<ApiResponse> call = RetrofitClient
//                            .getInstance()
//                            .getApi()
//                            .Verifyotp(mobileNo,getotp);
//                    call.enqueue(new Callback<ApiResponse>() {
//                        @Override
//                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                            try {
//                                if(response.body().getSuccess()==true)
//                                {
//                                    ApiResponse otpResponse = response.body();
//                                    String token=otpResponse.getData().getToken();
//                                    Preference.setAccessToken(otp.this,token);
//                                    Intent intent=new Intent(otp.this, profile.class);
//                                    startActivity(intent);
//                                }
//                                else
//                                {
//                                    Toast.makeText(otp.this,"Invalid OTP",Toast.LENGTH_LONG).show();
//                                    pin6.clearValue();
//                                }
//                                //Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ApiResponse> call, Throwable t) {
//                            Log.d("Failure",t.toString());
//                            //Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//            }
//        });
//        Didnt= (TextView)findViewById(R.id.no_otp);
//        Didnt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(otp.this, "Otp Resend Successfully", Toast.LENGTH_LONG).show();
//                        // This method will be executed once the timer is over
//                        mobileNo = "+91" + " " + MobileNo.getText().toString();
//                        Call<ApiResponse> call = RetrofitClient
//                                .getInstance()
//                                .getApi()
//                                .Sendotp(mobileNo);
//                        call.enqueue(new Callback<ApiResponse>() {
//                            @Override
//                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                                try {
//                                    String s = response.body().string();
//                                    Log.d("Success",s);
//                                    Intent intent= new Intent(otp.this, profile.class);
//                                    intent.putExtra("Mobile_no", mobileNo);
//                                    startActivity(intent);
//                                    //Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                                Log.d("Failure",t.toString());
//                                //Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                            }
//                        });
//                        finish();
//                    }
//                }, 10000);
//            }
//        });
    }

    private void startResendOtpTimer(){
        if(otpTimer==null)
            return;
        new CountDownTimer(60000,1000){
            public void onTick(long millisUntilFinished){
                String text = "Resend OTP in "+ (millisUntilFinished / 1000);
                otpTimer.setText(text);
            }

            public void onFinish(){
                otpTimer.setText("");
                resendOtpButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    /**
    Function that makes api call to send otp
    */
    private void sendOtpApi(String phone){
        Call<ApiResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .Sendotp(phone);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if(response.body().getSuccess()){
                        Toast.makeText(otp.this,"OTP resent successfully",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(otp.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Failure",t.toString());
                Toast.makeText(otp.this, "Some error occured",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     * Function to make api call to verify otp
     */
    public void verifyOtpApi(String phone, String otp){
        Call<ApiResponse> call = RetrofitClient
                .getInstance()
                .getApi().Verifyotp(phone,otp);
        // making both button invisible to prevent user intervention
        Toast.makeText(this, "Verifying OTP",Toast.LENGTH_SHORT).show();
        verifyOtpButton.setVisibility(View.INVISIBLE);
        resendOtpButton.setVisibility(View.INVISIBLE);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if(response.body().getSuccess()){

                        String token = response.body().getData().get("token").toString();
                        Preference.setAccessToken(otp.this, token);
                        Log.d("token",token);
                        Log.d("saved token", Preference.getAccessToken(otp.this));
                        Intent intent=new Intent(otp.this, profile.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(otp.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        verifyOtpButton.setVisibility(View.VISIBLE);
                        resendOtpButton.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Failure",t.toString());

                Toast.makeText(otp.this, "Some error occured",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void resendOtp(View v){
        startResendOtpTimer();
        if(resendOtpButton==null)
            return;
        resendOtpButton.setVisibility(View.INVISIBLE);
        sendOtpApi(phone);
    }

    public void verifyOtp(View v){
        if(verifyOtpButton==null)
            return;
        String otp = otpEditText.getText().toString();
        String regex = "\\d+";
        if(otp.length()==6 && otp.matches(regex)){
            verifyOtpApi(phone,otp);
        }
        else{
            Toast.makeText(this, "Enter a valid OTP",Toast.LENGTH_SHORT).show();
        }
    }
}
