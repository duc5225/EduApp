package com.example.eduapp.base.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.FragmentFactory;
import com.example.eduapp.base.itf.BaseScreen;
import com.example.eduapp.base.itf.OnCompleted;

public abstract class BaseDialogFragment<T extends BaseViewModel, Y extends ViewDataBinding> extends DialogFragment implements BaseScreen {

  protected int SIZE_WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
  protected int SIZE_MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;

  protected boolean isFullWith = false;

  protected boolean isFullHeight = false;

  protected float getWidth() {
    return SIZE_MATCH_PARENT;
  }

  protected float getHeight() {
    return SIZE_MATCH_PARENT;
  }

  protected T viewModel;
  protected Y binding;

  protected boolean isSetupViewCompleted;
  protected boolean disableInitViewModel = false;

  protected int getThemeAnimation() {
    return 0;
  }

  private DialogInterface.OnDismissListener onDismissListener;
  protected OnCompleted onCompleted;

  public BaseDialogFragment setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
    this.onDismissListener = onDismissListener;
    return this;
  }

  public BaseDialogFragment setOnOnCompleted(OnCompleted onCompleted) {
    this.onCompleted = onCompleted;
    return this;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    final Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    if (getThemeAnimation() != 0)
      dialog.getWindow().getAttributes().windowAnimations = getThemeAnimation();
    return dialog;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, idLayout(), container, false);
    binding.setLifecycleOwner(getViewLifecycleOwner());
    final View view = binding.getRoot();
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        hideKeyBoard();
        return false;
      }
    });
    return view;
  }

  protected void setupWindowDialog() {
    if (!isSetupViewCompleted) {
      isSetupViewCompleted = true;
      WindowManager manager = getActivity().getWindowManager();
      DisplayMetrics metrics = new DisplayMetrics();
      if (manager != null) {
        manager.getDefaultDisplay().getMetrics(metrics);
        int with = WindowManager.LayoutParams.WRAP_CONTENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        int statusBarHeight = 0;
        int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
          statusBarHeight = getActivity().getResources().getDimensionPixelSize(resourceId);
        }

        if (isFullWith || getWidth() == SIZE_MATCH_PARENT) {
          getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
          getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
          with = metrics.widthPixels;
        } else {
          if (getWidth() > 0 && getWidth() < 1) {
            with = (int) (metrics.widthPixels * getWidth());
          }
        }

        if (isFullHeight || getHeight() == SIZE_MATCH_PARENT) {
          height = SIZE_MATCH_PARENT;
        } else {
          if (getHeight() > 0 && getHeight() < 1) {
            height = (int) ((metrics.heightPixels - statusBarHeight) * getHeight());
          }
        }
        getDialog().getWindow().setLayout(with, height);
      }
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    hideKeyBoard();
    if (onDismissListener != null) {
      onDismissListener.onDismiss(null);
    }
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    doViewCreated(view);
  }

  @Override
  public void onStart() {
    super.onStart();
    setupWindowDialog();
  }

  @Override
  public boolean isScreenAlive() {
    if (getActivity() == null || getActivity().isDestroyed() || getActivity().isFinishing() || isRemoving() || isDetached()) {
      return false;
    }
    return true;
  }

  @Override
  public void showKeyBoard(View viewFocus) {
    FragmentFactory.showKeyboard(getActivity(), viewFocus);
  }

  @Override
  public void hideKeyBoard() {
    FragmentFactory.hideKeyboard(getActivity(), getView());
  }

  @Override
  public void changeColorStatusBar(int resColor) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getActivity().getWindow().setStatusBarColor(resColor);
    }
  }

  public void showDialogFragment(DialogFragment dialogFragment) {
    if (getActivity() != null) {
      FragmentFactory.showDialogFragment(getActivity(), dialogFragment);
    }
  }

  @Override
  public void showPopupMessage(String title, String message, DialogInterface.OnDismissListener onDismissListener) {
    FragmentFactory.showMessage(getActivity(), title, message, onDismissListener);
  }

  @Override
  public void showErrorMessage(String message, DialogInterface.OnDismissListener onDismissListener) {
    FragmentFactory.showErrorMessage(getActivity(), message, onDismissListener);
  }
}