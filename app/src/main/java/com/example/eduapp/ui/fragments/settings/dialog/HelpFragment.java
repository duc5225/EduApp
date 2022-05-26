package com.example.eduapp.ui.fragments.settings.dialog;

import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentHelpBinding;

public class HelpFragment extends BaseDialogFragment<BaseViewModel, FragmentHelpBinding> {
  @Override
  public int idLayout() {
    return R.layout.fragment_help;
  }

  @Override
  protected float getHeight() {
    return 1f;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
  }
}
