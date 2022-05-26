package com.example.eduapp.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class GlobalUtil {
  private final static String TAG = "ducpv";

  public static void makeToast(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }

  public static void makeLog(String log){
    Log.d(TAG, log);
  }
}
