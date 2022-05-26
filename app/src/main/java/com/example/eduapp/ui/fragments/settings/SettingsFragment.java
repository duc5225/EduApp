package com.example.eduapp.ui.fragments.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.databinding.FragmentProfileBinding;
import com.example.eduapp.databinding.FragmentSettingsBinding;
import com.example.eduapp.ui.activity.LoginActivity;
import com.example.eduapp.ui.activity.MainActivity;
import com.example.eduapp.ui.fragments.settings.dialog.EditProfileFragment;
import com.example.eduapp.ui.fragments.settings.dialog.HelpFragment;
import com.example.eduapp.ui.fragments.settings.dialog.TopupFragment;
import com.example.eduapp.ui.view.CustomViewPager;

public class SettingsFragment extends BaseFragment<BaseViewModel, FragmentProfileBinding> {
  @Override
  public int idLayout() {
    return R.layout.fragment_profile;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    binding.logoutButton.setOnClickListener(v -> {
      SharedPreferences.Editor editor = getActivity().getSharedPreferences("key", Context.MODE_PRIVATE).edit();
      editor.putBoolean("isLogin", false);
      editor.apply();

      Intent intent = new Intent(getActivity(), LoginActivity.class);
      getActivity().startActivity(intent);
      getActivity().finish();
    });

    binding.helpButton.setOnClickListener(v-> showDialogFragment(new HelpFragment()));

    binding.historyButton.setOnClickListener(v -> ((MainActivity) getActivity()).toPager(1));

    binding.editProfileButton.setOnClickListener(v -> showDialogFragment(new EditProfileFragment()));

    binding.topupButton.setOnClickListener(v-> showDialogFragment(new TopupFragment()));
  }
}