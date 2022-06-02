package com.example.eduapp.ui.fragments.search;

import static java.lang.Math.abs;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.eduapp.R;
import com.example.eduapp.base.helpers.HorizontalMarginItemDecoration;
import com.example.eduapp.base.itf.MatchSubjectClickListener;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.databinding.FragmentSearchBinding;
import com.example.eduapp.model.City;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.classdetail.ClassFragment;
import com.example.eduapp.ui.fragments.history.HistoryFragment;
import com.example.eduapp.ui.fragments.history.adapter.ClassRvAdapter;
import com.example.eduapp.ui.fragments.history.adapter.OnClassItemClick;
import com.example.eduapp.ui.fragments.userprofile.ProfileFragment;
import com.example.eduapp.util.GlobalUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment<SearchViewModel, FragmentSearchBinding> implements MatchSubjectClickListener, OnUserItemClick, OnClassItemClick {
  @Override
  public int idLayout() {
    return R.layout.fragment_search;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new SearchViewModel();

    int currentItem = 1;
    showLoadingView();
    setupViewpager(currentItem, viewModel.getData());
    viewModel.isStudent(this::setupRecyclerView);

    binding.filterBtn.setOnClickListener(v -> {
      showDialogFragment(new FragmentFilter());
    });

  }

  private void setupRecyclerView(Boolean isStudent) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    binding.userList.setLayoutManager(layoutManager);

    if (isStudent)
    viewModel.getAllTutors(object -> {
      UserListAdapter adapter = new UserListAdapter(object, this);
      binding.userList.setAdapter(adapter);
      binding.tvFindTitle.setText("Tìm kiếm giáo viên phù hợp");
      binding.numberTeachers.setText(object.size() + " giáo viên đang tìm kiếm học sinh");
      binding.rvTitle.setText("Các giáo viên nổi tiếng");
      hideLoadingView();
    });

    else {
      binding.tvFindTitle.setText("Tìm kiếm lớp học phù hợp");
      binding.rvTitle.setText("Các lớp học gần đây");
      viewModel.getAllClass(object -> {
        binding.numberTeachers.setText(object.size() + " lớp học đang chờ bạn nhận");
        List<String> usernameList = new ArrayList<>();
        for (Class aClass : object) {
          viewModel.getUserData(aClass.getUserId(), user -> {
            if(user == null) user = new User();
            usernameList.add(user.getFirstName() + " " + user.getLastName());
            if (object.size() == usernameList.size()){
              ClassRvAdapter adapter = new ClassRvAdapter(object, usernameList, viewModel, SearchFragment.this);
              binding.userList.setAdapter(adapter);
            }
          });
        }
        hideLoadingView();
      });
    }
  }


  private void setupViewpager(int currentItem, List<City> cityList) {
    CourseTopicsViewPager courseTopicsViewPager = new CourseTopicsViewPager(cityList, this);
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
  public void onScrollPagerItemClick(City courseCard, ImageView imageView) {
    GlobalUtil.makeToast(getContext(), courseCard.getName());
  }

  @Override
  public void onItemClick(User user) {
    showDialogFragment(new ProfileFragment(user));
  }

  @Override
  public void onItemClick(Class aClass, User user) {
    showDialogFragment(new ClassFragment(aClass, user));
  }
}