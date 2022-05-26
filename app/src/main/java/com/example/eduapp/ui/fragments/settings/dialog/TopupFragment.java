package com.example.eduapp.ui.fragments.settings.dialog;

import static java.lang.Math.abs;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.helpers.HorizontalMarginItemDecoration;
import com.example.eduapp.base.itf.TopupItemClickListener;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentTopupBinding;
import com.example.eduapp.model.MatchCourse;
import com.example.eduapp.ui.fragments.search.CourseTopicsViewPager;
import com.example.eduapp.ui.fragments.settings.adapter.TopupViewPager;

import java.util.List;

public class TopupFragment extends BaseDialogFragment<BaseViewModel, FragmentTopupBinding> implements TopupItemClickListener {
  @Override
  public int idLayout() {
    return R.layout.fragment_topup;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    setupViewpager(0);
  }

  private void setupViewpager(int currentItem) {
    TopupViewPager viewPager = new TopupViewPager(this);
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
  public void onQuestionClick() {
    showErrorMessage("Gửi đến tài khoản momo 0396854052, nội dung là mã thanh toán", null);
  }
}
