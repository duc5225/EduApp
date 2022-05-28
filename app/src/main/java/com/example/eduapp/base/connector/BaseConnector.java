package com.example.eduapp.base.connector;

import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.itf.RetrofitConnector;
import com.example.eduapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseConnector {
  public FirebaseAuth mAuth;
  public FirebaseDatabase mDatabase;
  private final String baseUrl = "https://eduapp-f8af5-default-rtdb.asia-southeast1.firebasedatabase.app";
  protected Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
  protected RetrofitConnector retrofitConnector = retrofit.create(RetrofitConnector.class);

  protected BaseConnector(){
    mAuth = FirebaseAuth.getInstance();
    mDatabase = FirebaseDatabase.getInstance(baseUrl);
  }

  private void handleRequest(Call call, OnCompleted onCompleted) {
    call.enqueue(new Callback() {
      @Override
      public void onResponse(Call call, Response response) {
        if (!response.isSuccessful()) {
          onCompleted.onError("Error: " + response.code());
          return;
        }
        onCompleted.onFinish(response.body());
      }

      @Override
      public void onFailure(Call call, Throwable t) {
        onCompleted.onError(t.toString());
      }
    });
  }
}
