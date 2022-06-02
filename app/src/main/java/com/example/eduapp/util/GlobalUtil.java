package com.example.eduapp.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GlobalUtil {
  private final static String TAG = "ducpv";

  public static void makeToast(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }

  public static void makeLog(String log) {
    Log.d(TAG, log);
  }

  public static String convertSubject(List<String> subjectIdList) {
    List<String> strings = new ArrayList<>();
    for (String string : subjectIdList) {
      if (string.endsWith("1")) strings.add("Toán");
      if (string.endsWith("2")) strings.add("Văn");
      if (string.endsWith("3")) strings.add("Tiếng Anh");
      if (string.endsWith("4")) strings.add("Lý");
      if (string.endsWith("5")) strings.add("Hoá");
      if (string.endsWith("6")) strings.add("CNTT");
    }
    return String.join(", ", strings);
  }

  public static String convertSubject(String subjectId) {
    switch (subjectId) {
      case "subject1":
        return "Toán";
      case "subject2":
        return "Văn";
      case "subject3":
        return "Tiếng anh";
      case "subject4":
        return "Lý";
      case "subject5":
        return "Hoá";
      default:
        return "CNTT";
    }
  }
  public static String convertCity(String cityId) {
    switch (cityId) {
      case "city1":
        return "Hà Nội";
      case "city2":
        return "Hồ Chí Minh";
      case "city3":
        return "Hưng Yên";
      case "city4":
        return "Hải Dương";
      default:
        return "Hải Phòng";
    }
  }
}
