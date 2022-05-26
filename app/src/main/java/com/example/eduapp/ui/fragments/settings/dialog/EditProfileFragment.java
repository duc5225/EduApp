package com.example.eduapp.ui.fragments.settings.dialog;

import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends BaseDialogFragment<BaseViewModel, FragmentEditProfileBinding> {
  @Override
  public int idLayout() {
    return R.layout.fragment_edit_profile;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
  }
}
