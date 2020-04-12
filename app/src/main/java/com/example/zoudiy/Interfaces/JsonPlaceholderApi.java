package com.example.zoudiy.Interfaces;

import com.example.zoudiy.Models.ApiResponse;
import com.example.zoudiy.Models.VehicleResponse;

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

    @FormUrlEncoded
    @POST("travel/vehicle/add-vehicle")
    Call<ApiResponse> addVehicle
            (
                    @Field("vehicleNo") String vehicleNo,
                    @Field("type") String type,
                    @Field("token") String token
            );

    @FormUrlEncoded
    @POST("travel/vehicle/get-vehicle")
    Call<VehicleResponse> getVehicle
            (
                    @Field("token") String token
            );

}
