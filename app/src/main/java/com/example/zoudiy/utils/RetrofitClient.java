package com.example.zoudiy.utils;

import com.example.zoudiy.Interfaces.JsonPlaceholderApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String Base_Url="https://zoudiy.com/";
    private static final String localTesting = "http://192.168.43.99:3000/";
    private static  RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(String url) {
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized RetrofitClient getInstance(){
        if(mInstance==null)
            mInstance = new RetrofitClient(Base_Url);
        return mInstance;
    }

    public static synchronized RetrofitClient getLocalInstance() {
        return new RetrofitClient(localTesting);
    }


    public JsonPlaceholderApi getApi(){
        return retrofit.create(JsonPlaceholderApi.class);
    }
}
