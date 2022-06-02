package com.example.eduapp.ui.fragments.notification;

import androidx.annotation.NonNull;

import com.example.eduapp.base.connector.BaseConnector;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotiConnector extends BaseConnector {
  public void getUserNotification(OnCompleted<List<Notification>> onCompleted) {
    String userId = mAuth.getCurrentUser().getUid();
    mDatabase.getReference("Users").child(userId).child("notification").orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        List<Notification> notifications = new ArrayList<>();
        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
          Notification notification = postSnapshot.getValue(Notification.class);
          notifications.add(notification);
        }
        onCompleted.onFinish(notifications);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError(error.toString());
      }
    });
  }
}
