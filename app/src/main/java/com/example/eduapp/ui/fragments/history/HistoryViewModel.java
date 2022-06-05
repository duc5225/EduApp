package com.example.eduapp.ui.fragments.history;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends BaseViewModel {
  public void getCUserData(OnCompleted<User> onCompleted){
    connector.getCUserData(onCompleted);
  }
  public void getAllClassOfUser(String userId, OnCompleted<List<Class>> onCompleted){
    connector.getAllClass(object -> {
      List<Class> classList = new ArrayList<>();
      for (Class aClass: object) {
        if (aClass.getUserId().equals(userId)){
          classList.add(aClass);
        }
      }
      onCompleted.onFinish(classList);
    });
  }

  public void getUserName(String id, OnCompleted<String> onCompleted){
    connector.getUser(id, object -> onCompleted.onFinish(object.getFirstName() + " " + object.getLastName()));
  }
  public void getCityName(String id, OnCompleted<String> onCompleted){
    connector.getCity(id, object -> onCompleted.onFinish(object.getName()));
  }
  public void getSubjectName(String id, OnCompleted<String> onCompleted){
    connector.getSubject(id, object -> onCompleted.onFinish(object.getName()));
  }

  public void setTaken(String classId, String userId, OnCompleted<String> onCompleted){
    connector.setTaken(classId, userId, onCompleted);
  }

  public void getAllClassUserHasTaken(String userID, OnCompleted<List<Class>> onCompleted) {
    connector.getAllClass(object -> {
      List<Class> classList = new ArrayList<>();
      for (Class aClass: object) {
        if (aClass.getTaken().equals(userID)){
          classList.add(aClass);
        }
      }
      onCompleted.onFinish(classList);
    });
  }

  public void getAllClass(OnCompleted<List<Class>> onCompleted){
    connector.getAllClass(onCompleted);
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

  public void deleteClass(Class aClass) {
    if (aClass.getTaken().equals(getCUid())){
      connector.removeTaken(aClass.getId(), aClass.getTaken());
    }

    else connector.deleteClass(aClass.getId());
  }
}
