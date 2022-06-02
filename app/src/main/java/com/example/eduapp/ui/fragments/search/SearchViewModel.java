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

  public List<City> getData() {
    return Arrays.asList(
//        new City(1, "Toán", "$12.00 USD", R.drawable.shop1),
//        new City(2, "Lý", "$50.00 USD", R.drawable.shop2),
//        new City(3, "Hoá", "$265.00 USD", R.drawable.shop3),
//        new City(4, "Văn", "$18.00 USD", R.drawable.shop4),
//        new City(5, "Anh", "$36.00 USD", R.drawable.shop5),
//        new City(6, "CNTT", "$145.00 USD", R.drawable.shop6),
        new City(1, "Hà Nội", "125 giáo viên", R.drawable.hanoi),
        new City(2, "Hồ Chí Minh", "150 giáo viên", R.drawable.hochiminh),
        new City(3, "Hưng Yên", "65 giáo viên", R.drawable.education_4),
        new City(4, "Đà Nẵng", "36 giáo viên", R.drawable.education_1),
        new City(5, "Nha Trang", "18 giáo viên", R.drawable.education_5),
        new City(6, "Hải Phòng", "14 giáo viên", R.drawable.education_6)
    );
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

  public void getAllClass(OnCompleted<List<Class>> onCompleted){
    connector.getAllClass(onCompleted);
  }
}
