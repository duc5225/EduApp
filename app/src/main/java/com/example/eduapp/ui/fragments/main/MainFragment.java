package com.example.eduapp.ui.fragments.main;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.base.helpers.GridSpacingItemDecoration;
import com.example.eduapp.base.itf.DashboardSubjectItemClickListener;
import com.example.eduapp.databinding.FragmentMainBinding;
import com.example.eduapp.model.SubjectCard;
import com.example.eduapp.ui.activity.MainActivity;
import com.example.eduapp.ui.fragments.createclass.PickerFragment;

import java.util.ArrayList;
import java.util.Date;

public class MainFragment extends BaseFragment<MainViewModel, FragmentMainBinding> implements DashboardSubjectItemClickListener {

  private ArrayList<SubjectCard> subjectCards;
  private CourseRecyclerAdapter adapter;

  @Override
  public int idLayout() {
    return R.layout.fragment_main;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
//    binding.editSearch.setEnabled(false);
    hideKeyBoard();
//    binding.editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//      @Override
//      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//          performSearch();
//          Toast.makeText(getContext(), "Edit Searching Click: " + binding.editSearch.getText().toString().trim(), Toast.LENGTH_SHORT).show();
//          return true;
//        }
//        return false;
//      }
//    });

    binding.editSearch.setOnClickListener(v -> {
      ((MainActivity) getActivity()).toPager(2);
    });
    binding.searchSection.setOnClickListener(v -> {
      Date time = new Date();
      PickerFragment pickerFragment = new PickerFragment(time, new PickerFragment.OnDateTimeSet() {
        @Override
        public void onSet(Date date) {

        }
      });

      showDialogFragment(pickerFragment);
    });
    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

    binding.rvSubject.setLayoutManager(layoutManager);
    binding.rvSubject.setClipToPadding(false);
    binding.rvSubject.setHasFixedSize(true);
    int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.normalPadding);
    binding.rvSubject.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, false, 0));

    subjectCards = new ArrayList<>();

    subjectCards.add(new SubjectCard(1, R.drawable.course_design_thinking, "Toán", "19 giáo viên"));
    subjectCards.add(new SubjectCard(2, R.drawable.course_design_coding, "Lý", "14 giáo viên"));
    subjectCards.add(new SubjectCard(3, R.drawable.course_design_marketing, "Hoá", "24 giáo viên"));
    subjectCards.add(new SubjectCard(4, R.drawable.course_design_securityexpert, "Văn", "18 giáo viên"));
    subjectCards.add(new SubjectCard(5, R.drawable.course_design_whatisthisshit, "Anh", "21 giáo viên"));
    subjectCards.add(new SubjectCard(6, R.drawable.course_coding, "CNTT", "10 giáo viên"));

    adapter = new CourseRecyclerAdapter(getContext(), subjectCards, this);

//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.card_margin);
//        binding.rvCourses.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

    binding.rvSubject.setAdapter(adapter);
  }

  private void performSearch() {
    binding.editSearch.clearFocus();
    InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    in.hideSoftInputFromWindow(binding.editSearch.getWindowToken(), 0);
  }

  @Override
  public void onDashboardSubjectClick(SubjectCard subjectCard, ImageView imageView) {
    Toast.makeText(getContext(), subjectCard.getSubjectTitle(), Toast.LENGTH_SHORT).show();
  }
}