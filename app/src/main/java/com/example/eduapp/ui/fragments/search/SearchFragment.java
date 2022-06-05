package com.example.eduapp.ui.fragments.search;

import static java.lang.Math.abs;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.eduapp.R;
import com.example.eduapp.base.FilterParam;
import com.example.eduapp.base.helpers.HorizontalMarginItemDecoration;
import com.example.eduapp.base.itf.MatchSubjectClickListener;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.databinding.FragmentSearchBinding;
import com.example.eduapp.model.City;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.classdetail.ClassFragment;
import com.example.eduapp.ui.fragments.history.adapter.ClassRvAdapter;
import com.example.eduapp.ui.fragments.history.adapter.OnClassItemClick;
import com.example.eduapp.ui.fragments.main.MainFragment;
import com.example.eduapp.ui.fragments.userprofile.ProfileFragment;
import com.example.eduapp.util.GlobalUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends BaseFragment<SearchViewModel, FragmentSearchBinding> implements MatchSubjectClickListener, OnUserItemClick, OnClassItemClick {
  @Override
  public int idLayout() {
    return R.layout.fragment_search;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = MainFragment.searchViewModel;

    int currentItem = 1;
    showLoadingView();
    viewModel.isStudent(isStudent -> {
      if (isStudent)
        viewModel.getCityUserCount(object -> setupViewpager(currentItem, createCityCard(true, object)));
      else
        viewModel.getCityClassCount(object -> setupViewpager(currentItem, createCityCard(false, object)));
      setupRecyclerView(isStudent);
    });

    binding.filterBtn.setOnClickListener(v -> {
      showDialogFragment(new FragmentFilter(viewModel));
    });

  }

  private void setupRecyclerView(Boolean isStudent) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    binding.userList.setLayoutManager(layoutManager);


    if (isStudent)
    viewModel.getAllTutors(object -> {
      viewModel.getFilterParamMutableLiveData().observe(this, object1 -> {
        List<User> users = viewModel.applyFilterUser(object, object1);
        UserListAdapter adapter = new UserListAdapter(users, this);
        binding.userList.setAdapter(adapter);
        binding.tvFindTitle.setText("Tìm kiếm giáo viên phù hợp");
        binding.numberTeachers.setText(object.size() + " giáo viên đang tìm kiếm học sinh");
        binding.rvTitle.setText("Các giáo viên nổi tiếng");
        hideLoadingView();
      });
    });

    else {
      binding.tvFindTitle.setText("Tìm kiếm lớp học phù hợp");
      binding.rvTitle.setText("Các lớp học gần đây");
      viewModel.getAllClass(classes -> {
        binding.numberTeachers.setText(classes.size() + " lớp học đang chờ bạn nhận");
        viewModel.getFilterParamMutableLiveData().observe(this, object1 -> {
          List<Class> filterClass = viewModel.applyFilterClass(classes, object1);
          List<String> usernameList = new ArrayList<>();
          for (Class aClass : filterClass) {
            viewModel.getUserData(aClass.getUserId(), user -> {
              if (user == null) user = new User();
              usernameList.add(user.getFirstName() + " " + user.getLastName());
              if (filterClass.size() == usernameList.size()) {
                ClassRvAdapter adapter = new ClassRvAdapter(filterClass, usernameList, viewModel, SearchFragment.this);
                binding.userList.setAdapter(adapter);
              }
            });
          }
          hideLoadingView();
        });
      });
    }
  }

  private List<City> createCityCard(Boolean isStudent, Integer[] object) {
    String prefix;
    if (isStudent) prefix = " giáo viên";
    else prefix = " lớp học";

    return Arrays.asList(new City(1, "Hà Nội", object[0] + prefix, R.drawable.hanoi), new City(2, "Hồ Chí Minh", object[1] + prefix, R.drawable.hochiminh), new City(3, "Hưng Yên", object[2] + prefix, R.drawable.hungyen), new City(4, "Hải Dương", object[3] + prefix, R.drawable.haiduong), new City(5, "Hải Phòng", object[4] + prefix, R.drawable.haiphong));
  }

  private void setupViewpager(int currentItem, List<City> cityList) {
    CourseTopicsViewPager courseTopicsViewPager = new CourseTopicsViewPager(cityList, this);
    binding.viewPager.setAdapter(courseTopicsViewPager);
    binding.viewPager.setCurrentItem(currentItem);
    binding.viewPager.setOffscreenPageLimit(1);


    if (getContext() != null){
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
    }

    binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
      }
    });
  }

  @Override
  public void onScrollPagerItemClick(Integer position) {
    FilterParam filterParam = viewModel.getFilterParamMutableLiveDataSnapshot();
    filterParam.city = position + 1;
    viewModel.setFilterParamMutableLiveData(filterParam);
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