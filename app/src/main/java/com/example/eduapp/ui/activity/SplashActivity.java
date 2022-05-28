package com.example.eduapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eduapp.databinding.ActivitySplashBinding;
import com.example.eduapp.ui.fragments.main.SharedModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

  ActivitySplashBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivitySplashBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

//    SharedModel sharedModel = new SharedModel();
//    Class aClass = sharedModel.isLogin(this) ? MainActivity.class : LoginActivity.class;

    Class aClass;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    if(currentUser != null) aClass = MainActivity.class;
    else aClass = LoginActivity.class;

    Intent intent = new Intent(this, aClass);
    final Handler handler = new Handler(Looper.getMainLooper());
    handler.postDelayed(() -> {
      startActivity(intent);
      finish();
    }, 3000);
  }
}