package com.example.eduapp.ui.fragments.userprofile;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentProfileBinding;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.classdetail.ClassFragment;
import com.example.eduapp.ui.fragments.history.adapter.ClassRvAdapter;
import com.example.eduapp.ui.fragments.history.adapter.OnClassItemClick;
import com.example.eduapp.ui.fragments.search.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ProfileFragment extends BaseDialogFragment<SearchViewModel, FragmentProfileBinding> implements OnClassItemClick {

  User user;

  public ProfileFragment(User user) {
    this.user = user;
  }

  @Override
  public int idLayout() {
    return R.layout.fragment_profile;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new SearchViewModel();

    showLoadingView();
    Glide.with(this).load(user.getImgUrl()).error(R.drawable.course_coding).centerCrop().apply(RequestOptions.bitmapTransform(new BlurTransformation(25))).into(binding.backgroundImage);

    Glide.with(getContext()).load(user.getImgUrl()).error(R.drawable.ic_user).circleCrop().into(binding.profileImage);

    binding.name.setText(user.getFirstName() + " " + user.getLastName());
    binding.role.setText(user.isStudent() ? "Sinh viên" : "Giáo viên");

    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    binding.rvClassOwned.setLayoutManager(layoutManager);

    viewModel.getUserLove(user.getId(), object -> {
      if (object == null) {
        binding.loveButton.setText("Thích");

        binding.loveButton.setOnClickListener(v -> {
          viewModel.addUserLove(user.getId());
        });
      } else {
        binding.loveButton.setText("Bỏ thích");

        binding.loveButton.setOnClickListener(v -> {
          viewModel.removeUserLove(user.getId());
        });
      }
    });

    binding.loveButton.setOnClickListener(v -> {
      viewModel.addUserLove(user.getId());
    });

    if (user.isStudent()) {
      viewModel.getAllClassOfUser(user.getId(), object -> {
        binding.classAmount.setText(object.size() + "");
        List<String> usernameList = new ArrayList<>();
        for (Class aClass : object) {
          usernameList.add(user.getFirstName() + " " + user.getLastName());
          if (object.size() == usernameList.size()) {
            ClassRvAdapter adapter = new ClassRvAdapter(object, usernameList, viewModel, ProfileFragment.this);
            binding.rvClassOwned.setAdapter(adapter);
          }
        }
        hideLoadingView();
      });
    } else viewModel.getAllClassUserHasTaken(user.getId(), object -> {
      binding.classAmount.setText(object.size() + "");
      List<String> usernameList = new ArrayList<>();
      for (Class aClass : object) {
        viewModel.getUserData(aClass.getUserId(), user -> {
          usernameList.add(user.getFirstName() + " " + user.getLastName());
          if (object.size() == usernameList.size()) {
            ClassRvAdapter adapter = new ClassRvAdapter(object, usernameList, viewModel, ProfileFragment.this);
            binding.rvClassOwned.setAdapter(adapter);
          }
        });
      }
      hideLoadingView();
    });
  }

  @Override
  public void onItemClick(Class aClass, User user) {
    showDialogFragment(new ClassFragment(aClass, user));
  }
}
