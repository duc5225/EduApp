package com.example.eduapp.ui.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduapp.R;
import com.example.eduapp.base.itf.OnCompleted;
import com.example.eduapp.base.ui.BaseFragment;
import com.example.eduapp.databinding.FragmentLoginBinding;
import com.example.eduapp.model.User;
import com.example.eduapp.ui.activity.MainActivity;
import com.example.eduapp.util.FragmentUtil;
import com.example.eduapp.util.GlobalUtil;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends BaseFragment<LoginViewModel, FragmentLoginBinding> {

    @Override
    public int idLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void doViewCreated(View view) {
        super.doViewCreated(view);

        viewModel = new LoginViewModel();

        final TextInputLayout usernameEditLayout = binding.signInUsernameLayout;
        final TextInputLayout passwordEditLayout = binding.signInPasswordLayout;
        final EditText usernameEditText = binding.signInUsernameEditText;
        final EditText passwordEditText = binding.signInPasswordEditText;
        final Button loginButton = binding.signInBtn;
        final TextView signupButton = binding.signUpBtn;

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.loginDataChanged(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        loginButton.setEnabled(false);

        viewModel.getUsername().observe(getViewLifecycleOwner(), s -> {
            if (isLegit(s)) {
                loginButton.setEnabled(true);
                usernameEditLayout.setError(null);
            } else {
                usernameEditLayout.setError("Username không hợp lệ");
                loginButton.setEnabled(false);
            }

        });
        viewModel.getPassword().observe(getViewLifecycleOwner(), s -> {
            if (s.length() > 6) {
                loginButton.setEnabled(true);
                passwordEditLayout.setError(null);
            } else {
                passwordEditLayout.setError("Mật khẩu không hợp lệ");
                loginButton.setEnabled(false);
            }

        });


        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onLoginPress();
            }
            return false;
        });

        loginButton.setOnClickListener(v -> onLoginPress());

        signupButton.setOnClickListener(v -> {
            FragmentUtil.replaceFragment(getActivity(), R.id.login_container, SignupFragment.class);
        });
    }

    private boolean isLegit(String username) {
        return username.length() > 8;
    }

    private void onLoginPress() {
        String username = binding.signInUsernameEditText.getText().toString();
        String password = binding.signInPasswordEditText.getText().toString();
        if (isLegit(username) && isLegit(password)) {
            binding.loading.setVisibility(View.VISIBLE);
            viewModel.login(username, password, new OnCompleted<User>() {
                @Override
                public void onFinish(User object) {

                }

                @Override
                public void onError(String error) {
                    OnCompleted.super.onError(error);
                    GlobalUtil.makeToast(getContext(), error);

                }
            });


        } else
            showLoginFailed("Hãy nhập đúng định dạng email và mật khẩu");

        Intent intent = new Intent(getActivity(), MainActivity.class);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            getActivity().startActivity(intent);
            getActivity().finish();
        }, 3000);

        if (binding.checkBox.isChecked()){
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("key", Context.MODE_PRIVATE).edit();
            editor.putBoolean("isLogin", true);
            editor.apply();
        }
        binding.loading.setVisibility(View.GONE);
    }

    private void showLoginFailed(String errorString) {
        binding.loading.setVisibility(View.GONE);
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(
                    getContext().getApplicationContext(),
                    errorString,
                    Toast.LENGTH_LONG).show();
        }
    }
}