package com.example.eduapp.ui.fragments.search;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.City;
import com.example.eduapp.model.User;

import java.util.List;

public class SearchViewModel extends BaseViewModel {

  SearchConnector searchConnector;

  public SearchViewModel() {
    searchConnector = new SearchConnector();
  }

  public List<City> getData() {
    return searchConnector.getData();
  }

  public void getAllTutors(OnCompleted<List<User>> onCompleted) {
    onCompleted.onFinish(searchConnector.getFakeUser());
  }
}
