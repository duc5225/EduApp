package com.example.eduapp.base.itf;

import android.widget.ImageView;

import com.example.eduapp.model.City;

public interface MatchSubjectClickListener {
  void onScrollPagerItemClick(City courseCard, ImageView imageView);
}
