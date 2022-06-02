package com.example.eduapp.ui.fragments.settings.adapter.topup;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseRvAdapter;
import com.example.eduapp.databinding.ItemTopupBinding;
import com.example.eduapp.model.Transaction;

import java.util.List;

public class TopupViewPagerAdapter extends BaseRvAdapter<ItemTopupBinding, List> {

  OnTopupItemClickListener onTopupItemClickListener;

  public TopupViewPagerAdapter(OnTopupItemClickListener onTopupItemClickListener) {
    this.onTopupItemClickListener = onTopupItemClickListener;
  }

  @Override
  public int itemLayoutId() {
    return R.layout.item_topup;
  }

  @Override
  public int size() {
    return 2;
  }

  @Override
  public void onBind(@NonNull MViewHolder holder, int position) {
    binding.amountEditText.setText("5000");
    //Bug
    if (position == 0) {
      binding.topupBtn.setText("Nạp");
      binding.topupBtn.setOnClickListener(v -> {
        Transaction transaction = new Transaction("Nạp", Integer.parseInt(binding.amountEditText.getText().toString()), true);
        onTopupItemClickListener.onButtonClick(transaction);
      });
    } else {
      binding.topupBtn.setText("Rút");
      binding.topupBtn.setOnClickListener(v -> {
        Transaction transaction = new Transaction("Rút", -Integer.parseInt(binding.amountEditText.getText().toString()), true);
        onTopupItemClickListener.onButtonClick(transaction);
      });
    }
    binding.question.setOnClickListener(v -> {
      onTopupItemClickListener.onQuestionMarkClick();
    });


    ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(mContext, R.array.method_array, android.R.layout.simple_spinner_item);
    roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    binding.spinnerMethod.setAdapter(roleAdapter);
  }
}
