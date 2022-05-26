package com.example.eduapp.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduapp.base.itf.BaseRAdapter;

import java.util.List;

public abstract class BaseRvAdapter<T extends ViewDataBinding, Y extends List> extends RecyclerView.Adapter<BaseRvAdapter.MViewHolder> implements BaseRAdapter {
  protected T binding;
  protected Y data;
  protected Context mContext;

  public BaseRvAdapter() {
  }

  @NonNull
  @Override
  public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    mContext = viewGroup.getContext();
    LayoutInflater layoutInflater = LayoutInflater.from(mContext);
    binding = DataBindingUtil.inflate(layoutInflater, itemLayoutId(), viewGroup, false);
    return new MViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
    onBind(holder, position);
  }

  public int getDimensionValuePixels(int dimension) {
    return (int) mContext.getResources().getDimension(dimension);
  }


  public int dpToPx(int dp) {
    float scale = mContext.getResources().getDisplayMetrics().density;
    return (int) (dp * scale + 0.5f);
  }

  @Override
  public int getItemCount() {
    return size();
  }

  public static class MViewHolder extends RecyclerView.ViewHolder {
    ViewDataBinding binding;

    public MViewHolder(@NonNull ViewDataBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}