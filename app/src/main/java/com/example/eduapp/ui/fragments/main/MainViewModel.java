package com.example.eduapp.ui.fragments.main;

import androidx.lifecycle.ViewModel;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Class;

import java.util.List;

public class MainViewModel extends BaseViewModel {
  MainConnector mainConnector = new MainConnector();

  public void getSubjectClassCount(OnCompleted<Integer[]> onCompleted){
    mainConnector.countSubjectClass(onCompleted);
  }
  public void getSubjectUserCount(OnCompleted<Integer[]> onCompleted){
    mainConnector.countSubjectUser(onCompleted);
  }
}
