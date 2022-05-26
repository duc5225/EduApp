package com.example.eduapp.ui.fragments.settings.dialog;

import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentHelperBinding;

public class HelperFragment extends BaseDialogFragment<BaseViewModel, FragmentHelperBinding> {

  @Override
  public int idLayout() {
    return R.layout.fragment_helper;
  }

  @Override
  protected float getWidth() {
    return 0.6f;
  }

  @Override
  protected float getHeight() {
    return 0.2f;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
  }
}
