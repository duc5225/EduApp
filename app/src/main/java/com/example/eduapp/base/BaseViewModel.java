package com.example.eduapp.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eduapp.base.connector.BaseConnector;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.FailDataModel;
import com.example.eduapp.model.Notification;
import com.example.eduapp.model.Transaction;
import com.example.eduapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class BaseViewModel extends ViewModel {
  public BaseConnector connector = new BaseConnector();

  public BaseViewModel() {
    failureLiveData = new MutableLiveData<>();
    filterParamMutableLiveData = new MutableLiveData<>();
    filterParamMutableLiveData.setValue(new FilterParam());
  }

  public final MutableLiveData<FailDataModel> failureLiveData;

  protected void handleFailure(int errorCode, String message) {
    failureLiveData.setValue(new FailDataModel(errorCode, message));
  }

  private final MutableLiveData<FilterParam> filterParamMutableLiveData;

  public String getCUid() {
    return connector.getCUid();
  }

  public void isStudent(OnCompleted<Boolean> onCompleted) {
    connector.isStudent(onCompleted);
  }

  public void addNotification(String userId, Notification notification) {
    connector.setNotification(userId, notification);
  }

  public void addClassLove(String classId){
    connector.setClassLove(classId);
  }
  public void removeClassLove(String classId){
    connector.removeClassLove(classId);
  }

  public void getClassLove(String classId, OnCompleted<Boolean> onCompleted){
    connector.getClassLove(classId, onCompleted);
  }

  public void addUserLove(String userId){
    connector.setUserLove(userId);
  }

  public void removeUserLove(String userId){
    connector.removeUserLove(userId);
  }

  public void getUserLove(String userId, OnCompleted<Boolean> onCompleted){
    connector.getUserLove(userId, onCompleted);
  }

  public void getAllClassLove(String userId, OnCompleted<List<Class>> onCompleted){
    connector.getAllClassLove(userId, onCompleted);
  }

  public void getAllUserLove(String userId, OnCompleted<List<User>> onCompleted) {
    connector.getAllUserLove(userId, onCompleted);
  }

  public void getUserData(String id, OnCompleted<User> onCompleted) {
    connector.getUser(id, onCompleted);
  }

  public void getUserDataOnce(String id, OnCompleted<User> onCompleted) {
    connector.getUserOnce(id, onCompleted);
  }

  public void addToken(String userId, Transaction transaction, OnCompleted<String> onCompleted) {
    connector.getToken(userId, new OnCompleted<Integer>() {
      @Override
      public void onFinish(Integer currentToken) {
        if (currentToken + transaction.getAmount() >= 0) {
          connector.setToken(userId, transaction, currentToken);
          if (transaction.getAmount() < 0) onCompleted.onFinish("Rút thành công");
          else onCompleted.onFinish("Nạp thành công");
        } else onCompleted.onError("Không đủ số dư tài khoản");
      }
    });
  }


  public MutableLiveData<FilterParam> getFilterParamMutableLiveData() {
    return filterParamMutableLiveData;
  }

  public FilterParam getFilterParamMutableLiveDataSnapshot() {
    return filterParamMutableLiveData.getValue();
  }

  public void setFilterParamMutableLiveData(FilterParam filterParam) {
    filterParamMutableLiveData.setValue(filterParam);
  }

  public void getSubjectClassCount(OnCompleted<Integer[]> onCompleted) {
    connector.countSubjectClass(onCompleted);
  }

  public void getSubjectUserCount(OnCompleted<Integer[]> onCompleted) {
    connector.countSubjectUser(onCompleted);
  }

  public void getCityClassCount(OnCompleted<Integer[]> onCompleted){
    connector.countCityClass(onCompleted);
  }
  public void getCityUserCount(OnCompleted<Integer[]> onCompleted){
    connector.countCityUser(onCompleted);
  }

  public List<User> applyFilterUser(List<User> users, FilterParam filterParam) {
    if (filterParam.toan && filterParam.van && filterParam.anh && filterParam.ly && filterParam.hoa && filterParam.cntt && filterParam.city == 0 && filterParam.scope == 0)
      return users;
    List<User> result = new ArrayList<>();
    for (User user : users){
      if (user.getSubjectList().contains("subject1") && filterParam.toan) {
        result.add(user);
        continue;
      }
      if (user.getSubjectList().contains("subject2") && filterParam.van) {
        result.add(user);
        continue;
      }
      if (user.getSubjectList().contains("subject3") && filterParam.anh) {
        result.add(user);
        continue;
      }
      if (user.getSubjectList().contains("subject4") && filterParam.ly) {
        result.add(user);
        continue;
      }
      if (user.getSubjectList().contains("subject5") && filterParam.hoa) {
        result.add(user);
        continue;
      }
      if (user.getSubjectList().contains("subject6") && filterParam.cntt) {
        result.add(user);
      }
    }

    switch (filterParam.city) {
      case 1:
        for (User user : users) if (!user.getCityId().equals("city1")) result.remove(user);
        break;
      case 2:
        for (User user : users) if (!user.getCityId().equals("city2")) result.remove(user);
        break;
      case 3:
        for (User user : users) if (!user.getCityId().equals("city3")) result.remove(user);
        break;
      case 4:
        for (User user : users) if (!user.getCityId().equals("city4")) result.remove(user);
        break;
      case 5:
        for (User user : users) if (!user.getCityId().equals("city5")) result.remove(user);
        break;
      default:
        break;
    }

    switch (filterParam.scope) {
      case 1:
        for (User user : users)
          if (!(user.getToken() != 0) || user.getToken() == null) result.remove(user);
        break;
      case 2:
        for (User user : users)
          if ((user.getToken() != 0) || user.getToken() != null) result.remove(user);
        break;
      default:
        break;
    }

    return result;
  }

  public List<Class> applyFilterClass(List<Class> classes, FilterParam filterParam) {
    if (filterParam.toan && filterParam.van && filterParam.anh && filterParam.ly && filterParam.hoa && filterParam.cntt && filterParam.city == 0 && filterParam.scope == 0)
      return classes;
    List<Class> result = new ArrayList<>();
    if (filterParam.toan)
      for (Class aClass : classes) if (aClass.getSubjectId().equals("subject1")) result.add(aClass);
    if (filterParam.van)
      for (Class aClass : classes) if (aClass.getSubjectId().equals("subject2")) result.add(aClass);
    if (filterParam.anh)
      for (Class aClass : classes) if (aClass.getSubjectId().equals("subject3")) result.add(aClass);
    if (filterParam.ly)
      for (Class aClass : classes) if (aClass.getSubjectId().equals("subject4")) result.add(aClass);
    if (filterParam.hoa)
      for (Class aClass : classes) if (aClass.getSubjectId().equals("subject5")) result.add(aClass);
    if (filterParam.cntt)
      for (Class aClass : classes) if (aClass.getSubjectId().equals("subject6")) result.add(aClass);

    switch (filterParam.city) {
      case 1:
        for (Class aClass : classes) if (!aClass.getCityId().equals("city1")) result.remove(aClass);
        break;
      case 2:
        for (Class aClass : classes) if (!aClass.getCityId().equals("city2")) result.remove(aClass);
        break;
      case 3:
        for (Class aClass : classes) if (!aClass.getCityId().equals("city3")) result.remove(aClass);
        break;
      case 4:
        for (Class aClass : classes) if (!aClass.getCityId().equals("city4")) result.remove(aClass);
        break;
      case 5:
        for (Class aClass : classes) if (!aClass.getCityId().equals("city5")) result.remove(aClass);
        break;
      default:
        break;
    }

    switch (filterParam.scope) {
      case 1:
        for (Class aClass : classes) if (aClass.getTaken().isEmpty()) result.remove(aClass);
        break;
      case 2:
        for (Class aClass : classes) if (!aClass.getTaken().isEmpty()) result.remove(aClass);
        break;
      default:
        break;
    }

    return result;
  }
}