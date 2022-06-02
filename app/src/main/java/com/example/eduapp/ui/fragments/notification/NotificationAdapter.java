package com.example.eduapp.ui.fragments.notification;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.eduapp.R;
import com.example.eduapp.base.BaseRvAdapter;
import com.example.eduapp.databinding.ItemNotifyBinding;
import com.example.eduapp.model.Notification;

import java.util.List;

public class NotificationAdapter extends BaseRvAdapter<ItemNotifyBinding, List<Notification>> {
  public NotificationAdapter(List<Notification> notifications){
    data = notifications;
  }
  @Override
  public int itemLayoutId() {
    return R.layout.item_notify;
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public void onBind(@NonNull MViewHolder holder, int position) {
    Notification notification = data.get(position);

    binding.notifyTitle.setText(notification.getTitle());
    binding.notifyMessage.setText(notification.getMessage());
    if (!notification.getExParam().getImgUrl().isEmpty() && notification.getType() == 4){
      Glide.with(mContext)
          .load(notification.getExParam().getImgUrl())
          .circleCrop()
          .into(binding.notifyImg);
    }
    else {
      switch (notification.getType()){
        case 1:
          binding.notifyImg.setImageResource(R.drawable.coin);
          break;
        case 2:
          binding.notifyImg.setImageResource(R.drawable.wallet);
          break;
        case 3:
          binding.notifyImg.setImageResource(R.drawable.article);
          break;
        case 4:
          binding.notifyImg.setImageResource(R.drawable.distance_learning);
          break;
        case 5:
          binding.notifyImg.setImageResource(R.drawable.presentation);
          break;
      }
    }
  }
}
