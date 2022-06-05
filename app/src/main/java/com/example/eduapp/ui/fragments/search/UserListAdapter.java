package com.example.eduapp.ui.fragments.search;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.eduapp.R;
import com.example.eduapp.base.BaseRvAdapter;
import com.example.eduapp.base.itf.BaseItemClickListener;
import com.example.eduapp.databinding.ItemPopularTeacherBinding;
import com.example.eduapp.model.User;
import com.example.eduapp.util.GlobalUtil;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class UserListAdapter extends BaseRvAdapter<ItemPopularTeacherBinding, List<User>> {
  OnUserItemClick onUserItemClick;

  public UserListAdapter(List<User> tutorList, OnUserItemClick onUserItemClick) {
    data = tutorList;
    this.onUserItemClick = onUserItemClick;
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
    User user = data.get(position);
    holder.itemView.setOnClickListener(v -> onUserItemClick.onItemClick(user));
    binding.name.setText(user.getFirstName() + " " + user.getLastName());
    binding.subject.setText(GlobalUtil.convertSubject(user.getSubjectList()));
    binding.price.setText((user.getPrice() * 1000) + "đ / 1h");

    Glide.with(mContext)
        .load(user.getImgUrl())
        .circleCrop().error(R.drawable.ic_user)
        .into(binding.profileImage);
  }
}
