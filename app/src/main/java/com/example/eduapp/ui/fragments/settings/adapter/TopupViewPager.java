package com.example.eduapp.ui.fragments.settings.adapter;

import androidx.annotation.NonNull;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseRvAdapter;
import com.example.eduapp.base.itf.BaseItemClickListener;
import com.example.eduapp.databinding.ItemTopupBinding;

import java.util.List;

public class TopupViewPager extends BaseRvAdapter<ItemTopupBinding, List> {

  BaseItemClickListener baseItemClickListener;
  public TopupViewPager(BaseItemClickListener baseItemClickListener) {
    this.baseItemClickListener = baseItemClickListener;
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
    if (position == 0) binding.topupBtn.setText("Nạp");
    else binding.topupBtn.setText("Rút");

    binding.question.setOnClickListener(v -> {
      baseItemClickListener.onItemClick();
    });
  }
}
