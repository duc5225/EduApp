package com.example.eduapp.ui.fragments.search;

import com.example.eduapp.R;
import com.example.eduapp.model.MatchCourse;

import java.util.Arrays;
import java.util.List;

public class SearchConnector {
  public List<MatchCourse> getData() {
    return Arrays.asList(
//        new MatchCourse(1, "Toán", "$12.00 USD", R.drawable.shop1),
//        new MatchCourse(2, "Lý", "$50.00 USD", R.drawable.shop2),
//        new MatchCourse(3, "Hoá", "$265.00 USD", R.drawable.shop3),
//        new MatchCourse(4, "Văn", "$18.00 USD", R.drawable.shop4),
//        new MatchCourse(5, "Anh", "$36.00 USD", R.drawable.shop5),
//        new MatchCourse(6, "CNTT", "$145.00 USD", R.drawable.shop6),
        new MatchCourse(1, "Hà Nội", "125 giáo viên", R.drawable.education_2),
        new MatchCourse(2, "Hồ Chí Minh", "150 giáo viên", R.drawable.education_3),
        new MatchCourse(3, "Hưng Yên", "65 giáo viên", R.drawable.education_4),
        new MatchCourse(4, "Đà Nẵng", "36 giáo viên", R.drawable.education_1),
        new MatchCourse(5, "Nha Trang", "18 giáo viên", R.drawable.education_5),
        new MatchCourse(6, "Hải Phòng", "14 giáo viên", R.drawable.education_6)
    );
  }

}
