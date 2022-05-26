package com.example.eduapp.ui.fragments.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.activity.MainActivity;
import com.example.eduapp.util.GlobalUtil;


public class LoginViewModel extends BaseViewModel {
  final private LoginConnector mModel = new LoginConnector();
  private MutableLiveData<String> username;
  private MutableLiveData<String> password;
  private User user;

  public User getUser() {
    if (user == null) {
      user = new User();
    }
    return user;
  }

  public MutableLiveData<String> getUsername() {
    if (username == null) {
      username = new MutableLiveData<String>();
    }
    return username;
  }

  public MutableLiveData<String> getPassword() {
    if (password == null) {
      password = new MutableLiveData<String>();
    }
    return password;
  }

  public void loginDataChanged(String username, String password) {
    getUsername().setValue(username);
    getPassword().setValue(password);
  }

  public void login(String username, String password, OnCompleted<User> onCompleted) {
    mModel.login(username, password, new OnCompleted<User>() {
      @Override
      public void onFinish(User user) {
        onCompleted.onFinish(user);
      }

      @Override
      public void onError(String error) {
        OnCompleted.super.onError(error);
        onCompleted.onError(error);
      }
    });
  }

  public void signup(User user, OnCompleted<String> onCompleted){
    if (user == null) onCompleted.onError("Hãy điền đầy đủ thông tin");

    else{
      mModel.register(user, object -> onCompleted.onFinish("success"));
    }
  }
}
