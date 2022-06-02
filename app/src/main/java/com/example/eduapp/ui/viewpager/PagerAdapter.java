package com.example.eduapp.ui.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.eduapp.ui.fragments.history.HistoryFragment;
import com.example.eduapp.ui.fragments.notification.NotiFragment;
import com.example.eduapp.ui.fragments.search.SearchFragment;
import com.example.eduapp.ui.fragments.settings.SettingsFragment;
import com.example.eduapp.ui.fragments.main.MainFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
  private static final int NUM_PAGES = 5;

  public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position){
      case 1: return new HistoryFragment();
      case 2: return new SearchFragment();
      case 3: return new NotiFragment();
      case 4: return new SettingsFragment();
      default: return new MainFragment();
    }
  }

  @Override
  public int getCount() {
    return NUM_PAGES;
  }
}


