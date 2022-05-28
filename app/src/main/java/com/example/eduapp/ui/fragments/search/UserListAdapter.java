package com.example.eduapp.ui.fragments.search;

import androidx.annotation.NonNull;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseRvAdapter;
import com.example.eduapp.base.itf.BaseItemClickListener;
import com.example.eduapp.databinding.ItemPopularTeacherBinding;
import com.example.eduapp.model.User;

import java.util.List;

public class UserListAdapter extends BaseRvAdapter<ItemPopularTeacherBinding, List<User>> {
  BaseItemClickListener baseItemClickListener;

  UserListAdapter(List<User> tutorList, BaseItemClickListener onItemClickListener) {
    data = tutorList;
    this.baseItemClickListener = onItemClickListener;
  }

  @Override
  public int itemLayoutId() {
    return R.layout.item_popular_teacher;
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public void onBind(@NonNull MViewHolder holder, int position) {
    holder.itemView.setOnClickListener(v -> baseItemClickListener.onItemClick());
    User item = data.get(position);
    binding.name.setText(item.getFirstName() + " " + item.getLastName());
    binding.subject.setText(item.getUserName());
    binding.price.setText(item.getImgUrl() + "đ / 1h");
  }
}
