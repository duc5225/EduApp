package com.example.eduapp.ui.fragments.login;

import android.view.View;
import android.widget.ArrayAdapter;

import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.databinding.FragmentSignupBinding;
import com.example.eduapp.model.User;
import com.example.eduapp.util.GlobalUtil;

public class SignupFragment extends BaseFragment<LoginViewModel, FragmentSignupBinding> {
  @Override
  public int idLayout() {
    return R.layout.fragment_signup;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    setupSpinner();

    viewModel = new LoginViewModel();

    binding.signUpBtn.setOnClickListener(v -> {
      String email = binding.signUpEmailEditText.getText().toString();
      String username = binding.signUpUsernameEditText.getText().toString();
      String password = binding.signUpPasswordEditText.getText().toString();
      String firstname = binding.signUpFirstnameEditText.getText().toString();
      String lastname = binding.signUpLastnameEditText.getText().toString();
      String phone = binding.signUpPhoneEditText.getText().toString();
      String address = binding.signUpAddressEditText.getText().toString();
      String dob = binding.dobButton.getText().toString();

      Integer isStudent = binding.spinnerGender.getSelectedItemPosition();
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

      User user = new User(email, username, password, firstname, lastname, phone, dob, gender, address, isStudent, "");

      viewModel.signup(user, object -> GlobalUtil.makeToast(getContext(), "Đăng ký thành công"));
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
}
