package com.example.eduapp.ui.fragments.settings;

import androidx.annotation.NonNull;

import com.example.eduapp.base.connector.BaseConnector;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SettingsConnector extends BaseConnector {
  public void logout(){
    mAuth.signOut();
  }

  public void getUserData(OnCompleted<User> onCompleted){
    FirebaseUser user = mAuth.getCurrentUser();
    DatabaseReference reference = mDatabase.getReference("Users");
    String userID = user.getUid();

    reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        User user = snapshot.getValue(User.class);
        onCompleted.onFinish(user);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        onCompleted.onError("Huỷ");
      }
    });
  }

  public void editProfile(User user, OnCompleted<String> onCompleted){
    mDatabase.getReference("Users")
        .child(mAuth.getCurrentUser().getUid())
        .setValue(user).addOnCompleteListener(task1 -> {
      if (task1.isSuccessful()) onCompleted.onFinish("Sửa profile thành công" );
      else onCompleted.onError("failure add info:" + task1.getException());
    });
  }
}
