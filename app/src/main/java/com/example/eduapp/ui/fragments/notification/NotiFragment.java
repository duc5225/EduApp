package com.example.eduapp.ui.fragments.notification;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.databinding.FragmentNotiBinding;

public class NotiFragment extends BaseFragment<NotiViewModel, FragmentNotiBinding> {

  NotificationAdapter notificationAdapter;
  @Override
  public int idLayout() {
    return R.layout.fragment_noti;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new NotiViewModel();

    showLoadingView();
    setupNotificationRv();
  }

  private void setupNotificationRv() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
    binding.rvNotify.setLayoutManager(layoutManager);

    viewModel.getUserNotification(object -> {
      notificationAdapter = new NotificationAdapter(object);
      binding.rvNotify.setAdapter(notificationAdapter);
      notificationAdapter.notifyDataSetChanged();
      hideLoadingView();
    });
  }
}