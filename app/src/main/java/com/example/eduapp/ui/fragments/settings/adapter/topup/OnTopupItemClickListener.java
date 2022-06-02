package com.example.eduapp.ui.fragments.settings.adapter.topup;

import com.example.eduapp.model.Transaction;

public interface OnTopupItemClickListener {
  void onQuestionMarkClick();
  void onButtonClick(Transaction transaction);
}
