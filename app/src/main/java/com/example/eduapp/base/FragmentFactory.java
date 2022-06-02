package com.example.eduapp.base;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eduapp.base.ui.SpinnerDialog;

public class FragmentFactory {
  public static void showKeyboard(FragmentActivity activity, View v) {
    if (activity == null || v == null) return;
    v.requestFocus();
    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
  }

  public static void hideKeyboard(FragmentActivity activity, View v) {
    if (activity == null || v == null) return;
    InputMethodManager imm =
        (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
  }

  public static synchronized void showDialogFragment(FragmentActivity activity, final DialogFragment dialogFragment) {
    if (activity == null || dialogFragment == null || activity.isDestroyed() || activity.isFinishing() || dialogFragment.isAdded())
      return;
    FragmentManager fm = activity.getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    Fragment oldFragment = fm.findFragmentByTag(dialogFragment.getClass().getCanonicalName());
    if (oldFragment != null) {
      transaction.remove(oldFragment);
    }
    dialogFragment.setRetainInstance(true);
    transaction.add(dialogFragment, dialogFragment.getClass().getCanonicalName());
    transaction.commitAllowingStateLoss();
    Log.e("noice", "Show dialog : " + dialogFragment.getClass().getCanonicalName());
    try {
      fm.executePendingTransactions();
    } catch (Exception e) {
      Log.e("noice", e.getMessage());
    }
  }

  public static void showMessage(FragmentActivity activity, String title, String message, DialogInterface.OnDismissListener onDismissListener) {
    if (activity == null || activity.isDestroyed() || activity.isFinishing()) return;

    new AlertDialog.Builder(activity)
        .setTitle(title)
        .setMessage(message)
        .setOnDismissListener(onDismissListener)
        .setPositiveButton("OK", null)
        .show();
  }

  public static void showErrorMessage(FragmentActivity activity, String message, DialogInterface.OnDismissListener onDismissListener) {
    if (activity == null || activity.isDestroyed() || activity.isFinishing()) return;

    new AlertDialog.Builder(activity)
        .setTitle("Thông báo")
        .setMessage(message)
        .setOnDismissListener(onDismissListener)
        .setPositiveButton("OK", null)
        .show();
  }

  public static void showConfirmDialog(FragmentActivity activity, String title, String message, DialogInterface.OnClickListener onPositiveButtonListener) {
    if (activity == null || activity.isDestroyed() || activity.isFinishing()) return;

    new AlertDialog.Builder(activity)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Đồng ý", onPositiveButtonListener)
        .setNegativeButton("Hủy", null)
        .setCancelable(true)
        .show();
  }

  public static synchronized void showLoadingDialog(FragmentActivity activity) {
    if (activity == null || activity.isDestroyed() || activity.isFinishing()) return;
    FragmentManager fm = activity.getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    Fragment oldFragment = fm.findFragmentByTag(SpinnerDialog.class.getCanonicalName());
    if (oldFragment != null) {
      transaction.remove(oldFragment);
    }
    final SpinnerDialog progressDialogFragment = new SpinnerDialog();
    transaction.add(progressDialogFragment, SpinnerDialog.class.getCanonicalName());
    transaction.commitAllowingStateLoss();
    try {
      fm.executePendingTransactions();
    }catch (Exception e) {

    }
  }
  public static synchronized void hideLoadingDialog(FragmentActivity activity) {
    if (activity == null || activity.isDestroyed() || activity.isFinishing()) return;
    FragmentManager fm = activity.getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    Fragment oldFragment = fm.findFragmentByTag(SpinnerDialog.class.getCanonicalName());
    if (oldFragment != null) {
      transaction.remove(oldFragment);
      transaction.commitAllowingStateLoss();
      try {
        fm.executePendingTransactions();
      }catch (Exception e) {

      }
    }
  }
}
