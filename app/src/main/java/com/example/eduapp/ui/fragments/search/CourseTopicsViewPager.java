package com.example.eduapp.ui.fragments.search;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.eduapp.R;
import com.example.eduapp.base.BaseRvAdapter;
import com.example.eduapp.base.itf.MatchSubjectClickListener;
import com.example.eduapp.databinding.ItemPagerCardBinding;
import com.example.eduapp.model.City;

import java.util.List;


public class CourseTopicsViewPager extends BaseRvAdapter<ItemPagerCardBinding, List<City>> {
  private MatchSubjectClickListener matchSubjectClickListener;

  public CourseTopicsViewPager(List<City> mCoursesList, MatchSubjectClickListener listener) {
    data = mCoursesList;
    this.matchSubjectClickListener = listener;
  }

  @Override
  public int itemLayoutId() {
    return R.layout.item_pager_card;
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public void onBind(@NonNull MViewHolder holder, int position) {
    City city = data.get(position);
    binding.tvCityName.setText(city.getName());
    binding.tvTeacherNumber.setText(city.getNumberOfCourses());
    Glide.with(mContext).load(city.getImageResource()).transform(new CenterCrop()).into(binding.image);

    binding.cardViewCourse.setOnClickListener(v -> matchSubjectClickListener.onScrollPagerItemClick(data.get(position), binding.image));
  }
}
