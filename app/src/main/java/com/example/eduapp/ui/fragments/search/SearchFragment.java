package com.example.eduapp.ui.fragments.search;

import static java.lang.Math.abs;

import android.view.View;
import android.widget.ImageView;

import androidx.viewpager2.widget.ViewPager2;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.base.helpers.HorizontalMarginItemDecoration;
import com.example.eduapp.base.itf.MatchSubjectClickListener;
import com.example.eduapp.databinding.FragmentSearchBinding;
import com.example.eduapp.model.MatchCourse;
import com.example.eduapp.util.GlobalUtil;

import java.util.List;

public class SearchFragment extends BaseFragment<SearchViewModel, FragmentSearchBinding> implements MatchSubjectClickListener {
  @Override
  public int idLayout() {
    return R.layout.fragment_search;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new SearchViewModel();

    int currentItem = 1;
    setupViewpager(currentItem, viewModel.getData());
  }

  private void setupViewpager(int currentItem, List<MatchCourse> matchCourseList) {
    CourseTopicsViewPager courseTopicsViewPager = new CourseTopicsViewPager(matchCourseList, this);
    binding.viewPager.setAdapter(courseTopicsViewPager);
    binding.viewPager.setCurrentItem(currentItem);
    binding.viewPager.setOffscreenPageLimit(1);

    int nextItemVisiblePx = (int) getResources().getDimension(R.dimen.viewpager_next_item_visible);
    int currentItemHorizontalMarginPx = (int) getResources().getDimension(R.dimen.viewpager_current_item_horizontal_margin);
    int pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx;

    ViewPager2.PageTransformer pageTransformer = (View page, float position) -> {
      page.setTranslationX(-pageTranslationX * position);
      page.setScaleY(1 - (0.15f * abs(position)));
      page.setAlpha(0.25f + (1 - abs(position)));
    };

    binding.viewPager.setPageTransformer(pageTransformer);
    binding.viewPager.addItemDecoration(new HorizontalMarginItemDecoration(
        getContext(), R.dimen.viewpager_current_item_horizontal_margin_testing,
        R.dimen.viewpager_next_item_visible_testing)
    );
    binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);

      }
    });
  }

  @Override
  public void onScrollPagerItemClick(MatchCourse courseCard, ImageView imageView) {
    GlobalUtil.makeToast(getContext(), courseCard.getName());
  }
}