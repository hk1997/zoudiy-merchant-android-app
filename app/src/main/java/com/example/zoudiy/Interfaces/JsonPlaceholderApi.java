package com.example.zoudiy.Interfaces;

import com.example.zoudiy.Models.ApiResponse;
import com.example.zoudiy.utils.example;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonPlaceholderApi {

    @FormUrlEncoded
    @POST("auth/send-otp")

    Call<ApiResponse> Sendotp
            (
                    @Field("phone") String mobileno
            );
    @FormUrlEncoded
    @POST("merchant/auth/verify-otp")
    Call<ApiResponse> Verifyotp
            (
                    @Field("phone") String mobileno,
                    @Field("otp") String Otp

            );
    @FormUrlEncoded
    @POST("merchant/profile/update-profile")
    Call<ApiResponse> updateProfile
               (
                    @Field("name") String name,
                    @Field("email") String email,
                    @Field("token") String token
            );

}
