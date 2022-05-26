package com.example.eduapp.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.eduapp.R;
import com.example.eduapp.base.helpers.BottomNavigationBehavior;
import com.example.eduapp.ui.viewpager.PagerAdapter;

public class MainActivity extends AppCompatActivity {

  com.example.eduapp.databinding.ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = com.example.eduapp.databinding.ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    initViewPager();
  }

  @Override
  protected void onStart() {
    super.onStart();
  }

  private void initViewPager() {
    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) binding.tab.getLayoutParams();
    layoutParams.setBehavior(new BottomNavigationBehavior());

    PagerAdapter adapter = new PagerAdapter(this.getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    binding.pager.setAdapter(adapter);
    binding.pager.setPagingEnabled(false);
    binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        switch (position) {
          case 0:
            binding.tab.getMenu().findItem(R.id.feed).setChecked(true);
            break;
          case 1:
            binding.tab.getMenu().findItem(R.id.history).setChecked(true);
            break;
          case 2:
            binding.tab.getMenu().findItem(R.id.search).setChecked(true);
            break;
          case 3:
            binding.tab.getMenu().findItem(R.id.noti).setChecked(true);
            break;
          case 4:
            binding.tab.getMenu().findItem(R.id.profile).setChecked(true);
            break;
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });

    binding.tab.setOnNavigationItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.feed:
          binding.pager.setCurrentItem(0);
          break;
        case R.id.history:
          binding.pager.setCurrentItem(1);
          break;
        case R.id.search:
          binding.pager.setCurrentItem(2);
          break;
        case R.id.noti:
          binding.pager.setCurrentItem(3);
          break;
        case R.id.profile:
          binding.pager.setCurrentItem(4);
      }
      return true;
    });
  }
  public void toPager(int pos){
    binding.pager.setCurrentItem(pos);
  }
}