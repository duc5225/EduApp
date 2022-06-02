package com.example.eduapp.base.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.eduapp.R;

public class SpinnerDialog extends DialogFragment {
  Dialog dialog;
  private DialogInterface.OnDismissListener onDismissListener;
  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    dialog = new ProgressDialog(getActivity(), R.style.NewDialog);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    this.setStyle(STYLE_NO_FRAME, getTheme()); // You can use styles or inflate a view
    dialog.setCancelable(false);
    return dialog;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (onDismissListener != null) {
      onDismissListener.onDismiss(null);
    }
  }

  public void setOnDismiss(DialogInterface.OnDismissListener onDismiss) {
    onDismissListener = onDismiss;
  }
}