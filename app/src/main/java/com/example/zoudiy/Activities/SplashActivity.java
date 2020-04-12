package com.example.zoudiy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                // if no access token is found, user needs to login
                Intent i = new Intent(SplashActivity.this, AppActivity.class);
                startActivity(i);
                finish();
//                if(Preference.getAccessToken(SplashActivity.this)==null){
//                    Intent i = new Intent(SplashActivity.this, number.class);
//                    startActivity(i);
//                    finish();
//                }
//                else{
//                    Intent i = new Intent(SplashActivity.this, profile.class);
//                    startActivity(i);
//                    finish();
//                }

            }
        }, 1000);
    }
}
