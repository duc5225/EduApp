package com.example.eduapp.ui.fragments.settings;

import android.content.Intent;
import android.view.View;

import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.databinding.FragmentSettingsBinding;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.activity.LoginActivity;
import com.example.eduapp.ui.activity.MainActivity;
import com.example.eduapp.ui.fragments.settings.dialog.EditProfileFragment;
import com.example.eduapp.ui.fragments.settings.dialog.HelpFragment;
import com.example.eduapp.ui.fragments.settings.dialog.TopupFragment;

public class SettingsFragment extends BaseFragment<SettingsViewModel, FragmentSettingsBinding> {

  @Override
  public int idLayout() {
    return R.layout.fragment_settings;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new SettingsViewModel();
    setupProfile();
    binding.logoutButton.setOnClickListener(v -> {
      viewModel.logout();
      Intent intent = new Intent(getActivity(), LoginActivity.class);
      getActivity().startActivity(intent);
      getActivity().finish();
    });

    binding.helpButton.setOnClickListener(v-> showDialogFragment(new HelpFragment()));

    binding.historyButton.setOnClickListener(v -> ((MainActivity) getActivity()).toPager(1));

    binding.editProfileButton.setOnClickListener(v -> showDialogFragment(new EditProfileFragment()));

    binding.topupButton.setOnClickListener(v-> showDialogFragment(new TopupFragment()));
  }

  private void setupProfile() {
    viewModel.getUserData(new OnCompleted<User>() {
      @Override
      public void onFinish(User user) {
        if (user != null){
          binding.settingName.setText(user.getFirstName() +  " " + user.getLastName());
          binding.settingRole.setText(user.isStudent() ? "Học sinh" : "Giáo viên");
          binding.settingEmail.setText(user.getEmail());
          binding.settingPhone.setText(user.getPhone());
        }
      }
    });
  }
}