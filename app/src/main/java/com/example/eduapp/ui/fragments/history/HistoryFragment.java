package com.example.eduapp.ui.fragments.history;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eduapp.R;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.databinding.FragmentHistoryBinding;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.classdetail.ClassFragment;
import com.example.eduapp.ui.fragments.history.adapter.ClassRvAdapter;
import com.example.eduapp.ui.fragments.history.adapter.OnClassItemClick;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends BaseFragment<HistoryViewModel, FragmentHistoryBinding> implements OnClassItemClick {
  @Override
  public int idLayout() {
    return R.layout.fragment_history;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = new HistoryViewModel();

    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    binding.rvClass.setLayoutManager(layoutManager);

    showLoadingView();
    viewModel.isStudent(isStudent -> {
      if (isStudent){
        viewModel.getAllClassOfUser(viewModel.getCUid(), object -> {
          List<String> usernameList = new ArrayList<>();
          for (Class aClass : object) {
            viewModel.getUserData(aClass.getUserId(), user -> {
              usernameList.add(user.getFirstName() + " " + user.getLastName());
              if (object.size() == usernameList.size()){
                ClassRvAdapter adapter = new ClassRvAdapter(object, usernameList, viewModel, HistoryFragment.this);
                binding.rvClass.setAdapter(adapter);
              }
            });
          }
          hideLoadingView();
        });
      }

      else {
        viewModel.getAllClassUserHasTaken(viewModel.getCUid(), object -> {
          List<String> usernameList = new ArrayList<>();
          for (Class aClass : object) {
            viewModel.getUserData(aClass.getTaken(), user -> {
              usernameList.add(user.getFirstName() + " " + user.getLastName());
              if (object.size() == usernameList.size()){
                ClassRvAdapter adapter = new ClassRvAdapter(object, usernameList, viewModel, HistoryFragment.this);
                binding.rvClass.setAdapter(adapter);
              }
            });
          }
          hideLoadingView();
          
        });
      }
    });


  }

  @Override
  public void onItemClick(Class aClass, User user) {
    showDialogFragment(new ClassFragment(aClass, user));
  }
}