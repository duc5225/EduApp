package com.example.eduapp.ui.fragments.settings;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Transaction;
import com.example.eduapp.model.User;

import java.util.List;

public class SettingsViewModel extends BaseViewModel {

  SettingsConnector settingsConnector = new SettingsConnector();
  public void logout() {
    settingsConnector.logout();
  }

  public void getUserData(OnCompleted<User> onCompleted) {
    settingsConnector.getCUserData(onCompleted);
  }

  public void editProfile(User user, OnCompleted<String> onCompleted){
    settingsConnector.getUser(user.getId(), new OnCompleted<User>() {
      @Override
      public void onFinish(User object) {

      }
    });
    settingsConnector.editProfile(user, onCompleted);
  }

  public void getUserTransaction(OnCompleted<List<Transaction>> onCompleted){
    settingsConnector.getUserTransaction(onCompleted);
  }
}
