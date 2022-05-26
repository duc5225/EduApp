package com.example.eduapp.ui.fragments.createclass;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.databinding.FragmentPickerBinding;

import java.util.Date;

public class PickerFragment extends BaseDialogFragment<BaseViewModel, FragmentPickerBinding> {
    OnDateTimeSet onDateTimeSet;
    Date time;

    @Override
    public int idLayout() {
        return R.layout.fragment_picker;
    }

    public PickerFragment(Date time, OnDateTimeSet onDateTimeSet) {
        this.time = time;
        this.onDateTimeSet = onDateTimeSet;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    protected float getWidth() {
        return 0.8f;
    }

    @Override
    protected float getHeight() {
        return 0.8f;
    }

    @Override
    public void doViewCreated(View view) {
        binding.datePicker.updateDate((int) time.getYear(), (int) time.getMonth() - 1, (int) time.getDay());

        binding.dateTimeSet.setOnClickListener(v -> {
            DatePicker datePicker = binding.datePicker;

            int year = datePicker.getYear();
            int month = datePicker.getMonth() + 1;
            int day = datePicker.getDayOfMonth();

            Date date = new Date(year, month, day);
            onDateTimeSet.onSet(date);
            dismiss();
        });
    }

    public interface OnDateTimeSet {
        void onSet(Date date);
    }
}
