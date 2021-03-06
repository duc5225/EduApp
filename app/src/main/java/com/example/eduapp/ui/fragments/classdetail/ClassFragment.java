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
      showConfirmDialog("X??c nh???n", "B???n c?? th???c s??? mu???n xo??", new DialogInterface.OnClickListener() {
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

        binding.deleteBtn.setText("Xo?? l???p");
        if (aClass.getUserId().equals(viewModel.getCUid())) binding.deleteBtn.setVisibility(View.VISIBLE);
        else binding.deleteBtn.setVisibility(View.GONE);
      } else {
        if (!aClass.getTaken().isEmpty()){
          binding.paddingBottom.setVisibility(View.GONE);
          binding.takeClassLayout.setVisibility(View.GONE);

          binding.deleteBtn.setText("Hu??? nh???n l???p");
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
          viewModel.addToken(viewModel.getCUid(), new Transaction("Nh???n l???p", aClass.getPrice(), false), object1 -> {});
          viewModel.addNotification(user.getId(), new Notification(4, new NotiExParam(aClass.getPrice(), aClass.getId(), aClass.getName(), user.getImgUrl())));
          viewModel.addToken(user.getId(), new Transaction("L???p ???????c nh???n", -aClass.getPrice(), false), object1 -> {});
          showErrorMessage("Nh???n l???p h???c th??nh c??ng", null);
          dismiss();
        }));
      }
    });

    binding.classTitle.setText("T??n l???p: " + aClass.getName());
    binding.authorName.setText("Ng?????i ????ng: " + user.getFirstName() + " " + user.getLastName());
    binding.state.setText("T??nh tr???ng: " + (aClass.getTaken().isEmpty() || aClass.getTaken() == null ? "Ch??a c?? ng?????i nh???n" : "???? c?? ng?????i nh???n"));
    binding.topic.setText("Ch??? ?????: " + aClass.getTopic());
    binding.length.setText("Th???i gian d???y: " + aClass.getLengthInMinute() + " ph??t");
    binding.address.setText("?????a ch???: " + aClass.getAddress() + " - " + GlobalUtil.convertCity(aClass.getCityId()));
    binding.phone.setText("??i???n tho???i: " + aClass.getPhone());
    binding.price.setText("Gi?? ti???n: " + aClass.getPrice() + " token");
    binding.form.setText("H??nh th???c h???c: " + (aClass.isOnline() ? "Online" : "Offline"));
    binding.time.setText("Th???i gian: " + aClass.getTime());

    binding.description.setText(aClass.getDescription());

  }
}
