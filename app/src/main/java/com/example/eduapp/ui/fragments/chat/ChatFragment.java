package com.example.eduapp.ui.fragments.chat;

import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.databinding.FragmentChatBinding;

public class ChatFragment extends BaseFragment<BaseViewModel, FragmentChatBinding> {
  public ChatFragment() {}

  @Override
  public int idLayout() {
    return R.layout.fragment_chat;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
  }
}