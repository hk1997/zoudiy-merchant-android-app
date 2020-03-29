package com.example.zoudiy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zoudiy.Models.ApiResponse;
import com.example.zoudiy.utils.Preference;
import com.example.zoudiy.utils.RetrofitClient;
import com.example.zoudiy.utils.example;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile extends AppCompatActivity {

    Button buttonLogout;
    Button buttonUpdate;
    EditText emailEditText, nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        buttonLogout = findViewById(R.id.button_profile_logout);
        buttonUpdate = findViewById(R.id.button_profile_update);
        emailEditText = findViewById(R.id.edit_text_profile_email);
        nameEditText = findViewById(R.id.edit_text_profile_name);
    }

    /**
     * Logout button handler
     * @param v
     */
    public void logout(View v){
        Preference.removeAccessToken(this);
        Intent intent = new Intent(this,number.class);
        startActivity(intent);
    }
    /**
     *  Function to make call to update api
     */
    private void updateApi(String name, String email){
        String token = Preference.getAccessToken(profile.this);
        token = token.substring(1,token.length()-1);
        Log.d("token in profile",token);
        Call<ApiResponse> call = RetrofitClient
                .getInstance()
                .getApi().updateProfile(name,email,token);
        // making both button invisible to prevent user intervention
        Toast.makeText(this, "Updating Profile",Toast.LENGTH_SHORT).show();
        buttonUpdate.setVisibility(View.INVISIBLE);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    Log.d("body of",response.body().getSuccess().toString());
                    Log.d("body of",response.body().getMessage().toString());
//                    Log.d("body of 2",);
                    if(response.body().getSuccess()){
                        Toast.makeText(profile.this, "Profile Updated Successfully",Toast.LENGTH_SHORT).show();
                        // redirect somewhere
                    }
                    else{
                        Toast.makeText(profile.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        buttonUpdate.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Failure",t.toString());

                Toast.makeText(profile.this, "Some error occured",Toast.LENGTH_SHORT).show();
                buttonUpdate.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Update button handler
     * @param v
     */
    public void update(View v){
        if(nameEditText==null || emailEditText == null)
            return;
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String emailRegex="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        //validating name and email
        if(name.length()==0){
            Toast.makeText(this,"Name is required", Toast.LENGTH_SHORT).show();
            return;
        }

        if(email.length()==0 || !email.matches(emailRegex)){
            Toast.makeText(this,"Invalid E-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        updateApi(name, email);
    }

    @Override
    public void onBackPressed(){
        // do nothing on back press
    }
}
