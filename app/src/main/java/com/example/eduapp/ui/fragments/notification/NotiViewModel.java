package com.example.eduapp.ui.fragments.notification;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Notification;

import java.util.List;

public class NotiViewModel extends BaseViewModel {
  NotiConnector notiConnector = new NotiConnector();

  public void getUserNotification(OnCompleted<List<Notification>> onCompleted){
    notiConnector.getUserNotification(onCompleted);
  }
}
