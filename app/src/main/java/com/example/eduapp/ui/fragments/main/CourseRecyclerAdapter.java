package com.example.eduapp.ui.fragments.main;

import android.content.Context;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseRvAdapter;
import com.example.eduapp.base.itf.DashboardSubjectItemClickListener;
import com.example.eduapp.databinding.ItemSubjectBinding;
import com.example.eduapp.model.Subject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseRecyclerAdapter extends BaseRvAdapter<ItemSubjectBinding, List<Subject>> {

  private DashboardSubjectItemClickListener coursesDashboardSubjectItemClickListener;

  public CourseRecyclerAdapter(List<Subject> mData, DashboardSubjectItemClickListener listener) {
    data = mData;
    this.coursesDashboardSubjectItemClickListener = listener;
  }

  public void setData(List<Subject> data){
    this.data = data;
    notifyDataSetChanged();
  }

  @Override
  public int itemLayoutId() {
    return R.layout.item_subject;
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public void onBind(@NonNull MViewHolder holder, int position) {
    Subject subject = data.get(position);
    final int pos = holder.getAdapterPosition();
    holder.itemView.setTag(pos);

    binding.cardViewImage.setImageResource(subject.getImageSubject());
    binding.stagItemCourse.setText(subject.getName());
    binding.stagItemQuantityCourse.setText(subject.getQuantitySubject());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        coursesDashboardSubjectItemClickListener.onDashboardSubjectClick(position);
      }
    });
  }
}