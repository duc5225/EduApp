package com.example.eduapp.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eduapp.model.FailDataModel;

public class BaseViewModel extends ViewModel {
  public BaseViewModel(){
    failureLiveData = new MutableLiveData<>();
  }
  public final MutableLiveData<FailDataModel> failureLiveData;
  protected void handleFailure(int errorCode, String message){
    failureLiveData.setValue(new FailDataModel(errorCode, message));
  }
}