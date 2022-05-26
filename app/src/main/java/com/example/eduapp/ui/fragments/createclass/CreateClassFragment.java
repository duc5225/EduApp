package com.example.eduapp.ui.fragments.createclass;

import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.databinding.FragmentCreateClassBinding;

public class CreateClassFragment extends BaseFragment<BaseViewModel, FragmentCreateClassBinding> {

  @Override
  public int idLayout() {
    return R.layout.fragment_create_class;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
  }
}