package com.example.zoudiy.Interfaces;

import com.example.zoudiy.Models.ApiResponse;
import com.example.zoudiy.Models.ListCityResponse;
import com.example.zoudiy.Models.ListRequestResponse;
import com.example.zoudiy.Models.ListSchoolResponse;
import com.example.zoudiy.Models.TripInfoResponse;
import com.example.zoudiy.Models.VehicleResponse;
import com.example.zoudiy.utils.AddTripClass;

import retrofit2.Call;
import retrofit2.http.Body;
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


    @POST("util/city/list-city")
    Call<ListCityResponse> listCities
            (

            );

    @FormUrlEncoded
    @POST("util/school/list-school")
    Call<ListSchoolResponse> listSchool
            (
                    @Field("cityId") String cityId
            );


    @POST("/travel/add-trip")
    Call<ApiResponse> addTrip(@Body AddTripClass addTrip);

    @FormUrlEncoded
    @POST("/travel/list-trip")
    Call<TripInfoResponse> listTrip(
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("/travel/list-request-merchant")
    Call<ListRequestResponse> listRequest(
            @Field("token") String token,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("/travel/serve-request")
    Call<ApiResponse> serveRequest(
            @Field("token") String token,
            @Field("accept") Boolean accept,
            @Field("requestId") String requestId
    );

}
