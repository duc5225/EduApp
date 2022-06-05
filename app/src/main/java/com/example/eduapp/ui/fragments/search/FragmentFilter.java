package com.example.eduapp.ui.fragments.search;

import android.view.View;
import android.widget.ArrayAdapter;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.FilterParam;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentFilterBinding;

public class FragmentFilter extends BaseDialogFragment<SearchViewModel, FragmentFilterBinding> {

  FragmentFilter(SearchViewModel searchViewModel){
    viewModel = searchViewModel;
  }
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

    FilterParam filter = viewModel.getFilterParamMutableLiveDataSnapshot();
    binding.toan.setChecked(filter.toan);
    binding.van.setChecked(filter.van);
    binding.eng.setChecked(filter.anh);
    binding.ly.setChecked(filter.ly);
    binding.hoa.setChecked(filter.hoa);
    binding.cntt.setChecked(filter.cntt);
    binding.spinnerCity.setSelection(filter.city);
    binding.spinnerScope.setSelection(filter.scope);

    binding.saveBtn.setOnClickListener(v->{
      Boolean toan = binding.toan.isChecked();
      Boolean van = binding.van.isChecked();
      Boolean anh = binding.eng.isChecked();
      Boolean ly = binding.ly.isChecked();
      Boolean hoa = binding.hoa.isChecked();
      Boolean cntt = binding.cntt.isChecked();
      Integer city = binding.spinnerCity.getSelectedItemPosition();
      Integer state = binding.spinnerScope.getSelectedItemPosition();
      FilterParam filterParam = new FilterParam(toan, van, anh, ly, hoa, cntt , city, state);

      viewModel.setFilterParamMutableLiveData(filterParam);

      dismiss();
    });
  }

  private void setupSpinner() {
    // city spinner
    ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(getContext(), R.array.city_array_2, android.R.layout.simple_spinner_item);
    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerCity.setAdapter(cityAdapter);

    // role spinner
    ArrayAdapter<CharSequence> scopeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.state_array, android.R.layout.simple_spinner_item);
    scopeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerScope.setAdapter(scopeAdapter);
  }
}
