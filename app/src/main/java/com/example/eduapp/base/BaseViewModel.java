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

import java.util.List;

public class BaseViewModel extends ViewModel {
  public BaseConnector connector = new BaseConnector();
  public BaseViewModel(){
    failureLiveData = new MutableLiveData<>();
  }
  public final MutableLiveData<FailDataModel> failureLiveData;
  protected void handleFailure(int errorCode, String message){
    failureLiveData.setValue(new FailDataModel(errorCode, message));
  }

  public String getCUid() {
    return connector.getCUid();
  }

  public void isStudent(OnCompleted<Boolean> onCompleted){
    connector.isStudent(onCompleted);
  }

  public void addNotification(String userId, Notification notification){
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

  public void getAllUserLove(String userId, OnCompleted<List<User>> onCompleted){
    connector.getAllUserLove(userId, onCompleted);
  }

  public void getUserData(String id, OnCompleted<User> onCompleted){
    connector.getUser(id, onCompleted);
  }

  public void addToken(String userId, Transaction transaction, OnCompleted<String> onCompleted){
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
}