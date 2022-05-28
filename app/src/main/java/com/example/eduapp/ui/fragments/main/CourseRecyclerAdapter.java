package com.example.eduapp.ui.fragments.main;

import android.content.Context;

import com.example.eduapp.base.itf.DashboardSubjectItemClickListener;
import com.example.eduapp.databinding.ItemSubjectBinding;
import com.example.eduapp.model.Subject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter._ViewHolder> {

  Context mContext;
  private List<Subject> mData;
  private DashboardSubjectItemClickListener coursesDashboardSubjectItemClickListener;

  public CourseRecyclerAdapter(Context mContext, List<Subject> mData, DashboardSubjectItemClickListener listener) {
    this.mContext = mContext;
    this.mData = mData;
    this.coursesDashboardSubjectItemClickListener = listener;
  }

  @NonNull
  @Override
  public CourseRecyclerAdapter._ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    LayoutInflater layoutInflater= LayoutInflater.from(viewGroup.getContext());
    ItemSubjectBinding itemSubjectBinding = ItemSubjectBinding.inflate(layoutInflater,viewGroup,false);
    return new _ViewHolder(itemSubjectBinding);
  }

  @Override
  public void onBindViewHolder(@NonNull _ViewHolder holder, int position, @NonNull List<Object> payloads) {
    super.onBindViewHolder(holder, position, payloads);
  }

  @Override
  public void onBindViewHolder(@NonNull final CourseRecyclerAdapter._ViewHolder viewHolder, final int i) {
    final int pos = viewHolder.getAdapterPosition();
    viewHolder.itemView.setTag(pos);

    viewHolder.setPostImage(mData.get(i));
    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        coursesDashboardSubjectItemClickListener.onDashboardSubjectClick(mData.get(i), viewHolder.itemSubjectBinding.cardViewImage);
      }
    });
  }

  public int getDimensionValuePixels(int dimension)
  {
    return (int) mContext.getResources().getDimension(dimension);
  }


  public int dpToPx(int dp)
  {
    float scale = mContext.getResources().getDisplayMetrics().density;
    return (int) (dp * scale + 0.5f);
  }


  @Override
  public long getItemId(int position) {
    Subject subject = mData.get(position);
    return subject.getId();
  }
  @Override
  public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override
  public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }

  @Override
  public int getItemCount() {
    return mData.size();
  }

  public static class _ViewHolder extends RecyclerView.ViewHolder{

    ItemSubjectBinding itemSubjectBinding;
    public _ViewHolder(@NonNull ItemSubjectBinding cardBinding) {
      super(cardBinding.getRoot());
      this.itemSubjectBinding = cardBinding;
    }

    void setPostImage(Subject subject){
      this.itemSubjectBinding.cardViewImage.setImageResource(subject.getImageSubject());
      this.itemSubjectBinding.stagItemCourse.setText(subject.getName());
      this.itemSubjectBinding.stagItemQuantityCourse.setText(subject.getQuantitySubject());
    }

  }
}