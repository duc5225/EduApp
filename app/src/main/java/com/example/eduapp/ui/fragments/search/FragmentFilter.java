package com.example.eduapp.ui.fragments.search;

import android.view.View;
import android.widget.ArrayAdapter;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentFilterBinding;

public class FragmentFilter extends BaseDialogFragment<BaseViewModel, FragmentFilterBinding> {
  @Override
  public int idLayout() {
    return R.layout.fragment_filter;
  }

  @Override
  protected float getHeight() {
    return 0.45f;
  }

  @Override
  protected float getWidth() {
    return 0.8f;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    setupSpinner();
  }

  private void setupSpinner() {
    // city spinner
    ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.city_array, android.R.layout.simple_spinner_item);
    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerCity.setAdapter(cityAdapter);

    // role spinner
    ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(getContext(), R.array.role_array, android.R.layout.simple_spinner_item);
    roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerRole.setAdapter(roleAdapter);
  }
}
