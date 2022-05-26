package com.example.eduapp.base.itf;

import android.widget.ImageView;

import com.example.eduapp.model.MatchCourse;

public interface MatchSubjectClickListener {
  void onScrollPagerItemClick(MatchCourse courseCard, ImageView imageView);
}
