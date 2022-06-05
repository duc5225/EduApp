package com.example.eduapp.ui.fragments.classdetail;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentClassBinding;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.NotiExParam;
import com.example.eduapp.model.Notification;
import com.example.eduapp.model.Transaction;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.history.HistoryViewModel;
import com.example.eduapp.ui.fragments.userprofile.ProfileFragment;
import com.example.eduapp.util.GlobalUtil;

public class ClassFragment extends BaseDialogFragment<HistoryViewModel, FragmentClassBinding> {
  Class aClass;
  User user;

  public ClassFragment(Class aClass, User user) {
    this.aClass = aClass;
    this.user = user;
  }

  @Override
  public int idLayout() {
    return R.layout.fragment_class;
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new HistoryViewModel();
    Glide.with(getContext()).load(user.getImgUrl()).error(R.drawable.ic_user).circleCrop().into(binding.authorImg);

    viewModel.getClassLove(aClass.getId(), object -> {
      if (object == null) {

        binding.loveButton.setImageResource(R.drawable.ic_heart_white);

        binding.loveButton.setOnClickListener(v -> {
          viewModel.addClassLove(aClass.getId());
        });
      } else {
        binding.loveButton.setImageResource(R.drawable.ic_heart);

        binding.loveButton.setOnClickListener(v -> {
          viewModel.removeClassLove(aClass.getId());
        });
      }
    });
    binding.authorImg.setOnClickListener(v -> showDialogFragment(new ProfileFragment(user)));
    binding.deleteBtn.setOnClickListener(v -> {
      showConfirmDialog("Xác nhận", "Bạn có thực sự muốn xoá", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          viewModel.deleteClass(aClass);
          dismiss();
        }
      });
    });
    viewModel.isStudent(isStudent -> {
      if (isStudent) {
        binding.paddingBottom.setVisibility(View.GONE);
        binding.takeClassLayout.setVisibility(View.GONE);

        binding.deleteBtn.setText("Xoá lớp");
        if (aClass.getUserId().equals(viewModel.getCUid())) binding.deleteBtn.setVisibility(View.VISIBLE);
        else binding.deleteBtn.setVisibility(View.GONE);
      } else {
        if (!aClass.getTaken().isEmpty()){
          binding.paddingBottom.setVisibility(View.GONE);
          binding.takeClassLayout.setVisibility(View.GONE);

          binding.deleteBtn.setText("Huỷ nhận lớp");
          if (aClass.getTaken().equals(viewModel.getCUid())) binding.deleteBtn.setVisibility(View.VISIBLE);
          else binding.deleteBtn.setVisibility(View.GONE);
        }
        else {
          binding.paddingBottom.setVisibility(View.VISIBLE);
          binding.takeClassLayout.setVisibility(View.VISIBLE);
          binding.deleteBtn.setVisibility(View.GONE);
        }

        binding.takeBtn.setOnClickListener(v -> viewModel.setTaken(aClass.getId(), viewModel.getCUid(), object -> {
          viewModel.addNotification(viewModel.getCUid(), new Notification(5, new NotiExParam(aClass.getPrice(), aClass.getId(), aClass.getName(), user.getImgUrl())));
          viewModel.addToken(viewModel.getCUid(), new Transaction("Nhận lớp", aClass.getPrice(), false), object1 -> {});
          viewModel.addNotification(user.getId(), new Notification(4, new NotiExParam(aClass.getPrice(), aClass.getId(), aClass.getName(), user.getImgUrl())));
          viewModel.addToken(user.getId(), new Transaction("Lớp được nhận", -aClass.getPrice(), false), object1 -> {});
          showErrorMessage("Nhận lớp học thành công", null);
          dismiss();
        }));
      }
    });

    binding.classTitle.setText("Tên lớp: " + aClass.getName());
    binding.authorName.setText("Người đăng: " + user.getFirstName() + " " + user.getLastName());
    binding.state.setText("Tình trạng: " + (aClass.getTaken().isEmpty() || aClass.getTaken() == null ? "Chưa có người nhận" : "Đã có người nhận"));
    binding.topic.setText("Chủ đề: " + aClass.getTopic());
    binding.length.setText("Thời gian dạy: " + aClass.getLengthInMinute() + " phút");
    binding.address.setText("Địa chỉ: " + aClass.getAddress() + " - " + GlobalUtil.convertCity(aClass.getCityId()));
    binding.phone.setText("Điện thoại: " + aClass.getPhone());
    binding.price.setText("Giá tiền: " + aClass.getPrice() + " token");
    binding.form.setText("Hình thức học: " + (aClass.isOnline() ? "Online" : "Offline"));
    binding.time.setText("Thời gian: " + aClass.getTime());

    binding.description.setText(aClass.getDescription());

  }
}
