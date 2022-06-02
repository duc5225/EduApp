package com.example.eduapp.ui.fragments.main;

import androidx.annotation.NonNull;

import com.example.eduapp.base.connector.BaseConnector;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Class;
import com.example.eduapp.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainConnector extends BaseConnector {
  public void countSubjectClass(OnCompleted<Integer[]> onCompleted){
    DatabaseReference reference = mDatabase.getReference("Class");

    reference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Integer[] count = {0,0,0,0,0,0};
        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
          Class aClass = postSnapshot.getValue(Class.class);
          for (Integer i = 0; i < 6; i++){
            if (aClass.getSubjectId().endsWith((i+1)+"")){
              count[i]++;
            }
          }
        }
        onCompleted.onFinish(count);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }

  public void countSubjectUser(OnCompleted<Integer[]> onCompleted){
    DatabaseReference reference = mDatabase.getReference("Users");

    reference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        Integer[] count = {0,0,0,0,0,0};
        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
          User user = postSnapshot.getValue(User.class);
          if (user.getSubjectList() != null)
          for (String string: user.getSubjectList()) {
            for (Integer i = 0; i < 6; i++){
              if (string.endsWith((i+1)+"")){
                count[i]++;
              }
            }
          }
        }
        onCompleted.onFinish(count);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }
}
