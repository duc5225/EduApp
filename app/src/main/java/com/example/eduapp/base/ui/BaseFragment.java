package com.example.eduapp.base.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.FragmentFactory;
import com.example.eduapp.base.itf.BaseScreen;

public abstract class BaseFragment<T extends BaseViewModel, Y extends ViewDataBinding> extends Fragment implements BaseScreen {
  protected T viewModel;
  protected Y binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, idLayout(), container, false);
    binding.setLifecycleOwner(getViewLifecycleOwner());
    binding.getRoot().setOnTouchListener((view, motionEvent) -> {
      BaseFragment.this.hideKeyBoard();
      view.performClick();
      return false;
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    doViewCreated(view);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (binding == null) return;
    FragmentFactory.hideKeyboard(getActivity(), binding.getRoot());
  }

  @Override
  public boolean isScreenAlive() {
    return getActivity() != null && !getActivity().isDestroyed() && !getActivity().isFinishing() && !isRemoving() && !isDetached();
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
    Window window = getActivity().getWindow();
    window.setStatusBarColor(ContextCompat.getColor(getActivity(),resColor));
  }

  @Override
  public void showDialogFragment(DialogFragment dialogFragment) {
    FragmentFactory.showDialogFragment(getActivity(), dialogFragment);
  }

  public void onBackPressed() {
    if (getActivity() != null) {
      hideKeyBoard();
      getActivity().onBackPressed();
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

  @Override
  public void showConfirmDialog(String title, String message, DialogInterface.OnClickListener onPositiveButtonListener) {
    FragmentFactory.showConfirmDialog(getActivity(), title, message, onPositiveButtonListener);
  }

  @Override
  public void showLoadingView() {
//    FragmentFactory.showLoadingDialog(getActivity());
  }

  @Override
  public void hideLoadingView() {
//    FragmentFactory.hideLoadingDialog(getActivity());
  }
}