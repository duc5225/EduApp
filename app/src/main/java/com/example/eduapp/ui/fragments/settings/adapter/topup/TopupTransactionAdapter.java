package com.example.eduapp.ui.fragments.settings.adapter.topup;

import androidx.annotation.NonNull;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseRvAdapter;
import com.example.eduapp.databinding.ItemTransactionBinding;
import com.example.eduapp.model.Transaction;

import java.util.List;

public class TopupTransactionAdapter extends BaseRvAdapter<ItemTransactionBinding, List<Transaction>> {

  public TopupTransactionAdapter(List<Transaction> transactions){
    data = transactions;
  }

  @Override
  public int itemLayoutId() {
    return R.layout.item_transaction;
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public void onBind(@NonNull MViewHolder holder, int position) {
    Transaction transaction = data.get(position);
    binding.transactionTitle.setText(transaction.getTitle());
    binding.amount.setText(transaction.getAmount() + " token");
    binding.paymentMethod.setText(transaction.getPaymentMethod());
    binding.typeImage.setImageResource(transaction.getAmount() > 0 ? R.drawable.coin : R.drawable.wallet);
  }
}
