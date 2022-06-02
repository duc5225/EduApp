package com.example.eduapp.ui.fragments.settings.dialog;

import static java.lang.Math.abs;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentTopupBinding;
import com.example.eduapp.model.NotiExParam;
import com.example.eduapp.model.Notification;
import com.example.eduapp.model.Transaction;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.settings.SettingsViewModel;
import com.example.eduapp.ui.fragments.settings.adapter.topup.OnTopupItemClickListener;
import com.example.eduapp.ui.fragments.settings.adapter.topup.TopupTransactionAdapter;
import com.example.eduapp.ui.fragments.settings.adapter.topup.TopupViewPagerAdapter;
import com.example.eduapp.util.GlobalUtil;

import java.util.List;

public class TopupFragment extends BaseDialogFragment<SettingsViewModel, FragmentTopupBinding> implements OnTopupItemClickListener {
  TopupTransactionAdapter topupTransactionAdapter;

  @Override
  public int idLayout() {
    return R.layout.fragment_topup;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new SettingsViewModel();

    viewModel.getUserData(new OnCompleted<User>() {
      @Override
      public void onFinish(User user) {
        binding.moneyCount.setText(user.getToken() == null ? 0 + "" : user.getToken() + "");
      }
    });
    setupViewpager(0);
    setupTransactionRv();
  }

  private void setupTransactionRv() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
    binding.historyTransactionRv.setLayoutManager(layoutManager);

    viewModel.getUserTransaction(new OnCompleted<List<Transaction>>() {
      @Override
      public void onFinish(List<Transaction> object) {
        topupTransactionAdapter = new TopupTransactionAdapter(object);
        binding.historyTransactionRv.setAdapter(topupTransactionAdapter);
        topupTransactionAdapter.notifyDataSetChanged();
      }
    });
  }

  private void setupViewpager(int currentItem) {
    TopupViewPagerAdapter viewPager = new TopupViewPagerAdapter(this);
    binding.viewPager.setAdapter(viewPager);
    binding.viewPager.setCurrentItem(currentItem);
    binding.viewPager.setOffscreenPageLimit(1);

    binding.depositBtn.setOnClickListener(v -> {
      binding.viewPager.setCurrentItem(0);
    });

    binding.withdrawBtn.setOnClickListener(v -> {
      binding.viewPager.setCurrentItem(1);
    });

    binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (position == 0) {
          binding.depositBtn.setBackgroundResource(R.drawable.left_round_corner_light);
          binding.withdrawBtn.setBackgroundResource(R.drawable.right_rounded_corner);
        }
        else {
          binding.depositBtn.setBackgroundResource(R.drawable.left_rounded_corner);
          binding.withdrawBtn.setBackgroundResource(R.drawable.right_round_conrner_light);
        }
      }
    });
  }

  @Override
  public void onQuestionMarkClick() {
    showErrorMessage("Gửi đến tài khoản momo 0396854052, nội dung là mã thanh toán", null);
  }

  @Override
  public void onButtonClick(Transaction transaction) {
    viewModel.addToken(viewModel.getCUid(), transaction, new OnCompleted<String>() {
      @Override
      public void onFinish(String object) {
        GlobalUtil.makeToast(getContext(), object);
        viewModel.addNotification(viewModel.getCUid(), new Notification(transaction.getAmount() > 0 ? 1 : 2, new NotiExParam(transaction.getAmount())));
      }

      @Override
      public void onError(String error) {
        OnCompleted.super.onError(error);
        GlobalUtil.makeToast(getContext(), error);
      }
    });
  }
}
