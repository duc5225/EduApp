package com.example.eduapp.ui.fragments.createclass;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.User;

public class CreateClassViewModel extends BaseViewModel {
  CreateClassConnector createClassConnector = new CreateClassConnector();

  public void createClass(Class clas, OnCompleted<String> onCompleted) {
    createClassConnector.createClass(clas, onCompleted);
  }

  public void getUserData(OnCompleted<User> onCompleted) {
    createClassConnector.getCUserData(onCompleted);
  }
}
