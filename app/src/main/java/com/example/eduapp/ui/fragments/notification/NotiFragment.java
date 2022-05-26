package com.example.eduapp.ui.fragments.notification;

import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.databinding.FragmentNotiBinding;

public class NotiFragment extends BaseFragment<BaseViewModel, FragmentNotiBinding> {

  @Override
  public int idLayout() {
    return R.layout.fragment_noti;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
  }
}