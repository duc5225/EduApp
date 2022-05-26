package com.example.eduapp.ui.fragments.search;

import com.example.eduapp.base.BaseViewModel;
import com.example.eduapp.model.MatchCourse;

import java.util.List;

public class SearchViewModel extends BaseViewModel {

  SearchConnector searchConnector;

  public SearchViewModel(){
    searchConnector = new SearchConnector();
  }
  public List<MatchCourse> getData() {
    return searchConnector.getData();
  }
}
