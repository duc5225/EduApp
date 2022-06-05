package com.example.eduapp.ui.fragments.search;

import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.City;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.fragments.history.HistoryViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchViewModel extends HistoryViewModel {
  public SearchViewModel() {
  }

  public void getAllTutors(OnCompleted<List<User>> onCompleted) {
    List<User> users = new ArrayList<>();
    connector.getAllUsers(new OnCompleted<List<User>>() {
      @Override
      public void onFinish(List<User> object) {
        for (User user: object) {
          if (!user.isStudent()){
            users.add(user);
          }
        }
        onCompleted.onFinish(users);
      }
    });
  }

  public void getAllTutorClass(String tutorId, OnCompleted<List<Class>> onCompleted){
    connector.getAllClass(new OnCompleted<List<Class>>() {
      @Override
      public void onFinish(List<Class> object) {
        List<Class> classList = new ArrayList<>();
        for (Class aClass: object) {
          if (aClass.getUserId().equals(tutorId)) classList.add(aClass);
        }
          onCompleted.onFinish(classList);
      }
    });
  }


}
