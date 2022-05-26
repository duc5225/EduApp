package com.example.eduapp.model;

import android.text.TextUtils;

public class FailDataModel {
  public String message = "";
  public int statusCode;

  public FailDataModel(int statusCode, String message) {
    if (!TextUtils.isEmpty(message)) {
      this.message = message;
    }
    this.statusCode = statusCode;
  }
}