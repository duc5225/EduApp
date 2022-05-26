package com.example.eduapp.ui.fragments.history;

import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.databinding.FragmentHistoryBinding;

public class HistoryFragment extends BaseFragment<BaseViewModel, FragmentHistoryBinding> {
  @Override
  public int idLayout() {
    return R.layout.fragment_history;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
  }
}