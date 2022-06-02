package com.example.eduapp.base.itf;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.DialogFragment;

public interface BaseScreen {
  int idLayout();
  default void doViewCreated(View view, Bundle savedInstanceState){}
  default void doViewCreated(View view){}

  void hideKeyBoard();
  boolean isScreenAlive();
  void showKeyBoard(View viewFocus);

  void showPopupMessage(String title, String message, DialogInterface.OnDismissListener onDismissListener);
  void showErrorMessage(String message, DialogInterface.OnDismissListener onDismissListener);
  void showDialogFragment(DialogFragment dialogFragment);
  void showLoadingView();
  void hideLoadingView();

  void changeColorStatusBar(int resColor);
}
