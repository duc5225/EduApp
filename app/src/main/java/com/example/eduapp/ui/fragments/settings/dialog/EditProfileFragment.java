package com.example.eduapp.ui.fragments.settings.dialog;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentEditProfileBinding;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.settings.SettingsViewModel;
import com.example.eduapp.util.GlobalUtil;

import java.util.ArrayList;
import java.util.List;

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

    showLoadingView();
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

    viewModel.getUserData(user -> {
      binding.profileEmailEditText.setText(user.getEmail());
      binding.profileUsernameEditText.setText(user.getUserName());
      binding.profileFirstnameEditText.setText(user.getFirstName());
      binding.profileLastnameEditText.setText(user.getLastName());
      binding.profilePhoneEditText.setText(user.getPhone());
      binding.profileAddressEditText.setText(user.getAddress());
      binding.profilePriceEditText.setText(user.getPrice().toString());
      if (user.getSubjectList() != null)

      for (String string : user.getSubjectList()) {
        if (string.endsWith("1")) binding.toan.setChecked(true);
        if (string.endsWith("2")) binding.van.setChecked(true);
        if (string.endsWith("3")) binding.eng.setChecked(true);
        if (string.endsWith("4")) binding.ly.setChecked(true);
        if (string.endsWith("5")) binding.hoa.setChecked(true);
        if (string.endsWith("6")) binding.cntt.setChecked(true);
      }
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

      hideLoadingView();
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

      List<String> subjectList = new ArrayList<>();
      if (binding.toan.isChecked()) subjectList.add("subject1");
      if (binding.van.isChecked()) subjectList.add("subject2");
      if (binding.eng.isChecked()) subjectList.add("subject3");
      if (binding.ly.isChecked()) subjectList.add("subject4");
      if (binding.hoa.isChecked()) subjectList.add("subject5");
      if (binding.cntt.isChecked()) subjectList.add("subject6");

      if (isStudent) subjectList.clear();

      viewModel.getUserDataOnce(viewModel.getCUid(), new OnCompleted<User>() {
        @Override
        public void onFinish(User object) {
          object.updateUser(email, username, firstname, lastname, phone, gender, address, "city1", isStudent, "", price, subjectList);
          viewModel.editProfile(object, new OnCompleted<String>() {
            @Override
            public void onFinish(String object) {
              showErrorMessage(object, null);
              dismiss();
            }
          });
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
