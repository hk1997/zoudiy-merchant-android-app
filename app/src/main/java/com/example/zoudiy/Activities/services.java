package com.example.zoudiy.Activities;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class  services extends AppCompatActivity {

    ImageView ob1,ob2,ob3,ob4,ob5,ob6,tick1,tick2,tick3,tick4,tick5,tick6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);
        ob1=(ImageView) findViewById(R.id.imageView2);
        tick1=(ImageView) findViewById(R.id.imageView10);
        ob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob1.setAlpha(0.5f);
                tick1.setVisibility(View.VISIBLE);
            }
        });

        ob2=(ImageView) findViewById(R.id.imageView3);
        tick2=(ImageView) findViewById(R.id.imageView11);
        ob2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob2.setAlpha(0.5f);
                tick2.setVisibility(View.VISIBLE);
            }
        });

        ob3=(ImageView) findViewById(R.id.imageView4);
        tick3=(ImageView) findViewById(R.id.imageView12);
        ob3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob3.setAlpha(0.5f);
                tick3.setVisibility(View.VISIBLE);
            }
        });

        ob4=(ImageView) findViewById(R.id.imageView5);
        tick4=(ImageView) findViewById(R.id.imageView13);
        ob4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob4.setAlpha(0.5f);
                tick4.setVisibility(View.VISIBLE);
            }
        });

        ob5=(ImageView) findViewById(R.id.imageView7);
        tick5=(ImageView) findViewById(R.id.imageView14);
        ob5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob5.setAlpha(0.5f);
                tick5.setVisibility(View.VISIBLE);
            }
        });

        ob6=(ImageView) findViewById(R.id.imageView8);
        tick6=(ImageView) findViewById(R.id.imageView15);
        ob6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ob6.setAlpha(0.5f);
                tick6.setVisibility(View.VISIBLE);
            }
        });

    }
}
