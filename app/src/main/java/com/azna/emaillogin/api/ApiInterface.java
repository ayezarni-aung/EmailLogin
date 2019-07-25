package com.azna.emaillogin.api;

import com.azna.emaillogin.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("register.php")
    @FormUrlEncoded
    Call<UserResponse> savePost(
            @Field("username")String username,
            @Field("email")String email,
            @Field("password")String password,
            @Field("confirm_password")String confirm_password
    );

    @POST("/login.php")
    @FormUrlEncoded
    Call<UserResponse> saveLogin(
            @Field("email")String email,
            @Field("password")String password
    );
}

