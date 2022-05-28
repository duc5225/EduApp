package com.example.eduapp.ui.fragments.settings.dialog;

import android.view.View;
import android.widget.ArrayAdapter;

import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentEditProfileBinding;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.settings.SettingsViewModel;
import com.example.eduapp.util.GlobalUtil;

public class EditProfileFragment extends BaseDialogFragment<SettingsViewModel, FragmentEditProfileBinding> {

  @Override
  public int idLayout() {
    return R.layout.fragment_edit_profile;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new SettingsViewModel();
    setupSpinner();

    viewModel.getUserData(user -> {
      binding.profileEmailEditText.setText(user.getEmail());
      binding.profileUsernameEditText.setText(user.getUserName());
      binding.profileFirstnameEditText.setText(user.getFirstName());
      binding.profileLastnameEditText.setText(user.getLastName());
      binding.profilePhoneEditText.setText(user.getPhone());
      binding.profileAddressEditText.setText(user.getAddress());
      binding.profilePriceEditText.setText(user.getPrice().toString());
      switch (user.getGender()) {
        case female:
          binding.spinnerGender.setSelection(1);
          break;
        case other:
          binding.spinnerGender.setSelection(2);
          break;
        default:
          binding.spinnerGender.setSelection(0);
      }
      binding.spinnerRole.setSelection(user.isStudent() ? 1 : 0);
    });

    binding.saveBtn.setOnClickListener(v->{
      String email = binding.profileEmailEditText.getText().toString();
      String username = binding.profileUsernameEditText.getText().toString();
      String firstname = binding.profileFirstnameEditText.getText().toString();
      String lastname = binding.profileLastnameEditText.getText().toString();
      String phone = binding.profilePhoneEditText.getText().toString();
      String address = binding.profileAddressEditText.getText().toString();
      Integer price = Integer.parseInt(binding.profilePriceEditText.getText().toString());

      boolean isStudent = binding.spinnerRole.getSelectedItemPosition() == 1;
      User.Gender gender;
      switch (binding.spinnerGender.getSelectedItemPosition()) {
        case 1:
          gender = User.Gender.female;
          break;
        case 2:
          gender = User.Gender.other;
          break;
        default:
          gender = User.Gender.male;
      }
      User user = new User(email, username, firstname, lastname, phone, gender, address, isStudent, "", price);

      viewModel.editProfile(user, new OnCompleted<String>() {
        @Override
        public void onFinish(String object) {
          GlobalUtil.makeToast(getContext(), object);
        }
      });
    });
  }

  private void setupSpinner() {
    // gender spinner
    ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getContext(), R.array.gender_array, android.R.layout.simple_spinner_item);
    genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerGender.setAdapter(genderAdapter);

    // role spinner
    ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(getContext(), R.array.role_array, android.R.layout.simple_spinner_item);
    roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerRole.setAdapter(roleAdapter);
  }

  private void getAllData() {

  }
}
