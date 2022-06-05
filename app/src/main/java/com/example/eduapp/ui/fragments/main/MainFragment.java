package com.example.eduapp.ui.fragments.main;

import android.view.View;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.eduapp.R;
import com.example.eduapp.base.FilterParam;
import com.example.eduapp.base.helpers.GridSpacingItemDecoration;
import com.example.eduapp.base.itf.DashboardSubjectItemClickListener;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.databinding.FragmentMainBinding;
import com.example.eduapp.model.Subject;
import com.example.eduapp.ui.activity.MainActivity;
import com.example.eduapp.ui.fragments.createclass.CreateClassFragment;
import com.example.eduapp.ui.fragments.search.SearchViewModel;

import java.util.ArrayList;

public class MainFragment extends BaseFragment<SearchViewModel, FragmentMainBinding> implements DashboardSubjectItemClickListener {

  ArrayList<Subject> subjects = new ArrayList<>();
  CourseRecyclerAdapter adapter;
  public static SearchViewModel searchViewModel = new SearchViewModel();

  @Override
  public int idLayout() {
    return R.layout.fragment_main;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = searchViewModel;

    hideKeyBoard();

    setupRv();

    showLoadingView();

    viewModel.isStudent(isStudent -> {
      if (isStudent) {
        binding.guideText.setText("Ấn để tạo lớp học theo ý muốn");
        binding.searchSection.setOnClickListener(v -> {
          showDialogFragment(new CreateClassFragment());
        });
        viewModel.getSubjectUserCount(object -> {
          hideLoadingView();
          createSubjectCard(true, object);
        });
      } else {
        binding.guideText.setText("Ấn để tìm kiếm lớp học ngay");
        binding.searchSection.setOnClickListener(v -> ((MainActivity) getActivity()).toPager(2));
        viewModel.getSubjectClassCount(object -> {
          hideLoadingView();
          createSubjectCard(false, object);
        });
      }
    });
    binding.editSearch.setOnClickListener(v -> ((MainActivity) getActivity()).toPager(2));
  }

  private void setupRv() {
    adapter = new CourseRecyclerAdapter(subjects, MainFragment.this);
    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    binding.rvSubject.setLayoutManager(layoutManager);
    binding.rvSubject.setClipToPadding(false);
    binding.rvSubject.setHasFixedSize(true);
    int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.normalPadding);
    binding.rvSubject.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, false, 0));
    binding.rvSubject.setAdapter(adapter);
  }

  private void createSubjectCard(Boolean isStudent, Integer[] object) {
    String prefix;
    if (isStudent) prefix = " giáo viên";
    else prefix = " lớp học";
    subjects.clear();

    subjects.add(new Subject(1, R.drawable.course_design_coding, "Toán", object[0] + prefix, isStudent));
    subjects.add(new Subject(2, R.drawable.course_design_thinking, "Văn", object[1] + prefix, isStudent));
    subjects.add(new Subject(3, R.drawable.course_design_securityexpert, "Tiếng Anh", object[2] + prefix, isStudent));
    subjects.add(new Subject(4, R.drawable.course_design_marketing, "Lý", object[3] + prefix, isStudent));
    subjects.add(new Subject(5, R.drawable.course_design_whatisthisshit, "Hoá", object[4] + prefix, isStudent));
    subjects.add(new Subject(6, R.drawable.course_coding, "CNTT", object[5] + prefix, isStudent));

    adapter.setData(subjects);
    binding.rvSubject.setAdapter(adapter);
  }

  @Override
  public void onDashboardSubjectClick(Integer position) {
    Boolean toan = false;
    Boolean van = false;
    Boolean anh = false;
    Boolean ly = false;
    Boolean hoa = false;
    Boolean cntt = false;

    switch (position) {
      case 0:
        toan = true;
        break;
      case 1:
        van = true;
        break;
      case 2:
        anh = true;
        break;
      case 3:
        ly = true;
        break;
      case 4:
        hoa = true;
        break;
      case 5:
        cntt = true;
        break;
      default:
        break;
    }
    viewModel.setFilterParamMutableLiveData(new FilterParam(toan, van, anh, ly, hoa, cntt, 0, 0));
    ((MainActivity) getActivity()).toPager(2);

  }
}