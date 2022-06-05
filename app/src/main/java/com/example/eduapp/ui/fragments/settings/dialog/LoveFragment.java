package com.example.eduapp.ui.fragments.settings.dialog;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseDialogFragment;
import com.example.eduapp.databinding.FragmentLoveBinding;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.classdetail.ClassFragment;
import com.example.eduapp.ui.fragments.history.adapter.ClassRvAdapter;
import com.example.eduapp.ui.fragments.history.adapter.OnClassItemClick;
import com.example.eduapp.ui.fragments.main.MainFragment;
import com.example.eduapp.ui.fragments.search.SearchViewModel;
import com.example.eduapp.ui.fragments.search.UserListAdapter;
import com.example.eduapp.ui.fragments.settings.adapter.love.OnLoveItemClickListener;
import com.example.eduapp.ui.fragments.userprofile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class LoveFragment extends BaseDialogFragment<SearchViewModel, FragmentLoveBinding> implements OnLoveItemClickListener {
  @Override
  public int idLayout() {
    return R.layout.fragment_love;
  }

  @Override
  public void doViewCreated(View view) {
    super.doViewCreated(view);
    viewModel = MainFragment.searchViewModel;

    setupRv();
  }

  private void setupRv() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    binding.rv.setLayoutManager(layoutManager);
    LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    binding.rv2.setLayoutManager(layoutManager2);

    viewModel.getAllClassLove(viewModel.getCUid(), new OnCompleted<List<Class>>() {
      @Override
      public void onFinish(List<Class> object) {
        List<String> usernameList = new ArrayList<>();
        for (Class aClass : object) {
          viewModel.getUserData(aClass.getUserId(), user -> {
            usernameList.add(user.getFirstName() + " " + user.getLastName());
            if (object.size() == usernameList.size()){
              ClassRvAdapter adapter = new ClassRvAdapter(object, usernameList, viewModel, (aClass1, user1) -> {
                showDialogFragment(new ClassFragment(aClass, user));
              });
              binding.rv.setAdapter(adapter);
              adapter.notifyDataSetChanged();
            }
          });
        }
      }
    });

    viewModel.getAllUserLove(viewModel.getCUid(), new OnCompleted<List<User>>() {
      @Override
      public void onFinish(List<User> object) {
        UserListAdapter adapter = new UserListAdapter(object, user -> {
          showDialogFragment(new ProfileFragment(user));
        });
        binding.rv2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
      }
    });
  }
}
