package com.example.zoudiy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zoudiy.utils.Preference;
import com.example.zoudiy.utils.RetrofitClient;
import com.example.zoudiy.utils.example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile extends AppCompatActivity {
  Button but1;
  EditText ed1,ed2;
  String token,emailid,fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        but1=(Button) findViewById(R.id.button2) ;
        ed1=(EditText) findViewById(R.id.editText);
        ed2=(EditText) findViewById(R.id.editText4);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                token= Preference.getAccessToken(profile.this);
                String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                emailid = ed2.getText().toString();
                fullname = ed1.getText().toString();
                Boolean isValid=  emailid.matches(emailPattern);
                if(emailid!=null && fullname!=null && emailid.matches(emailPattern)) {

                    Call<example> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .SaveProfile(emailid, fullname, token);
                    Log.d("token", token);
                    call.enqueue(new Callback<example>() {
                        @Override
                        public void onResponse(Call<example> call, Response<example> response) {
                            example responseBody = response.body();
                            Log.d("profile response", responseBody.getMessage() + " " + responseBody.getSuccess());
                            if (responseBody.getSuccess() == true) {
                                Toast.makeText(profile.this, "Profile Saved", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(profile.this, services.class);
                                startActivity(intent);
                                Log.d("yo here", "jsanfsanfkasf");
                            } else {
                                Toast.makeText(profile.this, "Incorrect Data", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<example> call, Throwable t) {
                            Log.d("Error", t.toString());
                        }
                    });
                }
                else{
                    Toast.makeText(profile.this,"Please provide valid Email Id",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    @Override
    public void onBackPressed(){

    }
}
