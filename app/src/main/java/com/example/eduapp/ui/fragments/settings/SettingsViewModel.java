package com.example.eduapp.ui.fragments.settings;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.settings.SettingsConnector;

public class SettingsViewModel extends BaseViewModel {

  SettingsConnector settingsConnector = new SettingsConnector();
  public void logout() {
    settingsConnector.logout();
  }

  public void getUserData(OnCompleted<User> onCompleted) {
    settingsConnector.getUserData(onCompleted);
  }

  public void editProfile(User user, OnCompleted<String> onCompleted){
    settingsConnector.editProfile(user, onCompleted);
  }
}
