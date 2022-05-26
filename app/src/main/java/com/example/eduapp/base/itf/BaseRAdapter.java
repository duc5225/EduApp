package com.example.eduapp.base.itf;

import androidx.annotation.NonNull;

import com.example.eduapp.base.BaseRvAdapter;

public interface BaseRAdapter {
  int itemLayoutId();
  int size();
  void onBind(@NonNull BaseRvAdapter.MViewHolder holder, int position);
}
