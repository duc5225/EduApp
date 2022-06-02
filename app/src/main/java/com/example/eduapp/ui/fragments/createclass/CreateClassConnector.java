package com.example.eduapp.ui.fragments.createclass;

import com.example.eduapp.base.connector.BaseConnector;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Class;

public class CreateClassConnector extends BaseConnector {
  void createClass(Class clas, OnCompleted<String> onCompleted) {
    mDatabase.getReference("Class").child(clas.getId()).setValue(clas).addOnCompleteListener(task1 -> {
      if (task1.isSuccessful()) onCompleted.onFinish("Success");
      else onCompleted.onError("failure add info:" + task1.getException());
    });
  }
}
