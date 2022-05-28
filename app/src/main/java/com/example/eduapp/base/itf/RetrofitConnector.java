package com.example.eduapp.base.itf;

import com.example.eduapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitConnector {

  @GET("users")
  Call<List<User>> getAllUser();

  @GET("user/{id}")
  Call<User> getUser(@Path("id") String id);

  @GET("/api/tutor")
  Call<List<User>> getAllTutor();

  @POST("auth/register")
  Call<User> register(@Body User user);

  @POST("auth/login")
  Call<User> login(@Body User user);
}
