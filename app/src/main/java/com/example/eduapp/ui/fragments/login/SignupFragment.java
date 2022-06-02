package com.example.eduapp.ui.fragments.login;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.databinding.FragmentSignupBinding;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.activity.MainActivity;
import com.example.eduapp.util.GlobalUtil;

import java.util.ArrayList;
import java.util.List;

public class SignupFragment extends BaseFragment<LoginViewModel, FragmentSignupBinding> {
  @Override
  public int idLayout() {
    return R.layout.fragment_signup;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new LoginViewModel();

    setupSpinner();
    premadeData();

    binding.signUpBtn.setOnClickListener(this::onSignupClick);
  }

  private void premadeData() {
    binding.signUpEmailEditText.setText("ducvip5225@gmail.com");
    binding.signUpUsernameEditText.setText("hedasxd");
    binding.signUpPasswordEditText.setText("Hedas0612");
    binding.signUpFirstnameEditText.setText("Phan Việt");
    binding.signUpLastnameEditText.setText("Đức");
    binding.signUpPhoneEditText.setText("0396854052");
    binding.signUpAddressEditText.setText("Số 1, Phạm Văn Đồng, Cầu Giấy");
    binding.signUpPriceEditText.setText("90000");
  }

  private void setupSpinner() {
    //city spinner
    ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.city_array, android.R.layout.simple_spinner_item);
    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerCity.setAdapter(cityAdapter);
    // gender spinner
    ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getContext(), R.array.gender_array, android.R.layout.simple_spinner_item);
    genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerGender.setAdapter(genderAdapter);

    // role spinner
    ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(getContext(), R.array.role_array, android.R.layout.simple_spinner_item);
    roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerRole.setAdapter(roleAdapter);

    binding.spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) binding.forTutor.setVisibility(View.VISIBLE);
        else binding.forTutor.setVisibility(View.GONE);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  private void onSignupClick(View v) {
    String email = binding.signUpEmailEditText.getText().toString();
    String username = binding.signUpUsernameEditText.getText().toString();
    String password = binding.signUpPasswordEditText.getText().toString();
    String firstname = binding.signUpFirstnameEditText.getText().toString();
    String lastname = binding.signUpLastnameEditText.getText().toString();
    String phone = binding.signUpPhoneEditText.getText().toString();
    String address = binding.signUpAddressEditText.getText().toString();
    String city = "city" + (binding.spinnerCity.getSelectedItemPosition() + 1);
    Integer price = Integer.parseInt(binding.signUpPriceEditText.getText().toString());
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

    List<String> subjectList = new ArrayList<>();
    if (binding.toan.isChecked()) subjectList.add("subject1");
    if (binding.van.isChecked()) subjectList.add("subject2");
    if (binding.eng.isChecked()) subjectList.add("subject3");
    if (binding.ly.isChecked()) subjectList.add("subject4");
    if (binding.hoa.isChecked()) subjectList.add("subject5");
    if (binding.cntt.isChecked()) subjectList.add("subject6");

    User user = new User(email, username, password, firstname, lastname, phone, gender, address, city, isStudent, "", price, 0, subjectList);

    viewModel.signup(user, new OnCompleted<String>() {
      @Override
      public void onFinish(String object) {
        GlobalUtil.makeToast(SignupFragment.this.getContext(), "Đăng ký thành công");
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
      }

      @Override
      public void onError(String error) {
        OnCompleted.super.onError(error);
        showErrorMessage(error, null);
      }
    });
  }
}
