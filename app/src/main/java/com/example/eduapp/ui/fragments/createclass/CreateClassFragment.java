package com.example.eduapp.ui.fragments.createclass;

import android.view.View;
import android.widget.ArrayAdapter;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentCreateClassBinding;
import com.example.eduapp.model.Class;
import com.example.eduapp.util.GlobalUtil;

import java.util.UUID;

public class CreateClassFragment extends BaseDialogFragment<CreateClassViewModel, FragmentCreateClassBinding> {

  @Override
  public int idLayout() {
    return R.layout.fragment_create_class;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new CreateClassViewModel();

    setupSpinner();
    setupPreMadeData();

    binding.postBtn.setOnClickListener(this::onPostBtnClick);

    binding.plus.setOnClickListener(v -> {
      Integer getMin = Integer.parseInt(binding.lengthEdit.getText().toString() == null ? "0" : binding.lengthEdit.getText().toString());
      binding.lengthEdit.setText((getMin + 30) + "");
    });
    binding.minus.setOnClickListener(v -> {
      Integer getMin = Integer.parseInt(binding.lengthEdit.getText().toString() == null ? "0" : binding.lengthEdit.getText().toString());
      if (getMin >= 30) {
        binding.lengthEdit.setText((getMin - 30) + "");
      }
    });
  }

  private void setupPreMadeData() {

    binding.classTitle.setText("Toán cho học sinh lớp 3");
    binding.topicEdit.setText("Toán lớp 3");
    binding.lengthEdit.setText("30");
    binding.addressEdit.setText("Số 1, Phạm Văn Đồng, Cầu giấy");
    viewModel.getUserData(user -> {
      binding.phoneEdit.setText(user.getPhone());
    });
    binding.descriptionEdit.setText("Lớp học dành cho các cháu lớp 3 có hoàn cảnh khó khăn");
    binding.timeEdit.setText("Chủ nhật hàng tuần");
    binding.priceEdit.setText("100");
  }

  private void setupSpinner() {
    ArrayAdapter<CharSequence> subjectAdapter = ArrayAdapter.createFromResource(getContext(), R.array.subject_array, android.R.layout.simple_spinner_item);
    subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerSubject.setAdapter(subjectAdapter);

    ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.city_array, android.R.layout.simple_spinner_item);
    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerCity.setAdapter(cityAdapter);
  }

  private void onPostBtnClick(View v) {
    String id = UUID.randomUUID().toString();
    String userId = viewModel.getCUid();
    String name = binding.classTitle.getText().toString();
    String subject = "subject" + (binding.spinnerSubject.getSelectedItemPosition() + 1);
    String topic = binding.topicEdit.getText().toString();
    Integer lengthInMinute = Integer.parseInt(binding.lengthEdit.getText().toString());
    String address = binding.addressEdit.getText().toString();
    String phone = binding.phoneEdit.getText().toString();
    String cityId = "city" + (binding.spinnerSubject.getSelectedItemPosition() + 1);
    Integer price = Integer.parseInt(binding.priceEdit.getText().toString());
    boolean isOnline = binding.radio.getCheckedRadioButtonId() != R.id.offline;
    String description = binding.descriptionEdit.getText().toString();
    String time = binding.timeEdit.getText().toString();

    Class clas = new Class(id, userId, name, subject, topic, lengthInMinute, address, phone, cityId, price, isOnline, description, time, "");

    viewModel.createClass(clas, object -> {
      GlobalUtil.makeToast(getContext(), object);
      dismiss();
    });
  }
}