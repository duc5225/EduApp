package com.example.eduapp.base.connector;

import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.itf.RetrofitConnector;
import com.example.eduapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseConnector {
  private final String baseUrl = "https://www.youtube.com/";
  protected Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
  protected RetrofitConnector retrofitConnector = retrofit.create(RetrofitConnector.class);

  public void getAllUsers(OnCompleted<List<User>> onCompleted) {
    Call<List<User>> getAllUser = retrofitConnector.getAllUser();
    handleRequest(getAllUser, onCompleted);
  }

  public void getUser(String id, OnCompleted<User> onCompleted) {
    Call<User> getUser = retrofitConnector.getUser(id);
    handleRequest(getUser, onCompleted);
  }

  public void register(User user, OnCompleted<User> onCompleted) {
    Call<User> register = retrofitConnector.register(user);
    handleRequest(register, onCompleted);
  }

  public void login(String username, String password, OnCompleted<User> onCompleted) {
    User user = new User(username, password);
    Call<User> login = retrofitConnector.login(user);
    handleRequest(login, onCompleted);
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
