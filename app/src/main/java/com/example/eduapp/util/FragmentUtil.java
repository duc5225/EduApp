package com.example.eduapp.util;

import androidx.fragment.app.FragmentActivity;

public class FragmentUtil {
  public static void replaceFragment(FragmentActivity activity, int containerId, Class clas){
    activity.getSupportFragmentManager().beginTransaction()
        .setReorderingAllowed(true)
        .replace(containerId, clas, null)
        .addToBackStack(null)
        .commit();
  }
}
