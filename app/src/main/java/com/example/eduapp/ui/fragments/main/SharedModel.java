package com.example.eduapp.ui.fragments.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedModel {
  public boolean isLogin(Activity activity) {
    SharedPreferences sharedPref = activity.getSharedPreferences("key", Context.MODE_PRIVATE);
    return sharedPref.getBoolean("isLogin", false);
  }
}
