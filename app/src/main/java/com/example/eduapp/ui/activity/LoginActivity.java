package com.example.eduapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.eduapp.databinding.ActivityLoginBinding;
import com.example.eduapp.ui.fragments.login.LoginFragment;

public class LoginActivity extends AppCompatActivity {

  ActivityLoginBinding binding;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .setReorderingAllowed(true)
          .add(binding.loginContainer.getId(), LoginFragment.class, null)
          .commit();
    }
  }
}