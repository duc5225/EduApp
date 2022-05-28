package com.example.eduapp.ui.fragments.search;

import static com.example.eduapp.model.User.Gender.male;

import com.example.eduapp.R;
import com.example.eduapp.base.connector.BaseConnector;
import com.example.eduapp.model.City;
import com.example.eduapp.model.User;

import java.util.Arrays;
import java.util.List;

public class SearchConnector extends BaseConnector {
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

  public List<User> getFakeUser() {
    return Arrays.asList(
        new User("email@gmail.com","Toán, Lý, Anh" ,"Phan Việt", "Đức", "0396854052", male,  "Ha noi",  false,"", 90000),
        new User("email@gmail.com","Toán, CNTT" ,"Nguyễn Trọng", "0396854052","Thường", male,  "Ha noi",  false,"", 100000),
        new User("email@gmail.com","Văn, Anh","Lê Mạnh", "0396854052","Cường", male,  "Ha noi",  false,"", 20000)
    );
  }
}
