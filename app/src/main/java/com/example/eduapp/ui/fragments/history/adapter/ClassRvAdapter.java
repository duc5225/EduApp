package com.example.eduapp.ui.fragments.history.adapter;

import androidx.annotation.NonNull;

import com.example.eduapp.R;
import com.example.eduapp.base.BaseRvAdapter;
import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.databinding.ItemClassPreviewBinding;
import com.example.eduapp.model.Class;
import com.example.eduapp.util.GlobalUtil;

import java.util.List;

public class ClassRvAdapter extends BaseRvAdapter<ItemClassPreviewBinding, List<Class>> {

  BaseViewModel baseViewModel;
  OnClassItemClick onClassItemClick;
  List<String> usernameList;

  public ClassRvAdapter(List<Class> classList, List<String> usernameList, BaseViewModel baseViewModel, OnClassItemClick onClassItemClick) {
    data = classList;
    this.usernameList = usernameList;
    this.baseViewModel = baseViewModel;
    this.onClassItemClick = onClassItemClick;
  }

  @Override
  public int itemLayoutId() {
    return R.layout.item_class_preview;
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public void onBind(@NonNull MViewHolder holder, int position) {
    Class mClass = data.get(position);

    holder.itemView.setOnClickListener(v -> baseViewModel.getUserData(mClass.getUserId(), user -> {
      onClassItemClick.onItemClick(mClass, user);
    }));


    binding.classCreator.setText(usernameList.get(position));
    binding.classTitle.setText(mClass.getName());

    binding.classAddress.setText(mClass.getAddress() + " - " + GlobalUtil.convertCity(mClass.getCityId()));
    binding.classSubject.setText(GlobalUtil.convertSubject(mClass.getSubjectId()));

    binding.classPrice.setText((mClass.getPrice() * 1000) + "đ / 1h");
  }
}
