package com.example.eduapp.ui.fragments.login;

import androidx.annotation.NonNull;

import com.example.eduapp.base.connector.BaseConnector;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

public class LoginConnector extends BaseConnector {

  LoginConnector(){
    super();
  }

  void signup(User user, OnCompleted<String> onCompleted){
    mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
        .addOnCompleteListener(task -> {
          if (task.isSuccessful()) {

            user.setPassword(null);
            user.setId(mAuth.getCurrentUser().getUid());
            mDatabase.getReference("Users")
            .child(mAuth.getCurrentUser().getUid())
            .setValue(user).addOnCompleteListener(task1 -> {
              if (task1.isSuccessful()) onCompleted.onFinish("Success");
              else onCompleted.onError("failure add info:" + task1.getException());
            });

          } else {
            // If sign in fails, display a message to the user.
            onCompleted.onError("failure create account:" + task.getException());
          }
        });
  }

  void login(String email, String password, OnCompleted<String> onCompleted){
    mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(task -> {
          if (task.isSuccessful()) {
            onCompleted.onFinish("Successful");
          } else {
            onCompleted.onError("failure login:" + task.getException());
          }
        });
  }

}
