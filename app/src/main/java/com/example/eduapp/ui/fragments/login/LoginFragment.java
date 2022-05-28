package com.example.eduapp.ui.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
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

        final TextInputLayout emailEditLayout = binding.signInEmailLayout;
        final TextInputLayout passwordEditLayout = binding.signInPasswordLayout;
        final EditText emailEditText = binding.signInEmailEditText;
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
                viewModel.loginDataChanged(emailEditText.getText().toString(),
                    passwordEditText.getText().toString());
            }
        };
        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        loginButton.setEnabled(false);

        viewModel.getUsername().observe(getViewLifecycleOwner(), s -> {
            if (isLegit(s)) {
                loginButton.setEnabled(true);
                emailEditLayout.setError(null);
            } else {
                emailEditLayout.setError("Hãy nhập đúng định dạng email");
                loginButton.setEnabled(false);
            }

        });
        viewModel.getPassword().observe(getViewLifecycleOwner(), s -> {
            if (isLegitP(s)) {
                loginButton.setEnabled(true);
                passwordEditLayout.setError(null);
            } else {
                passwordEditLayout.setError("Mật khẩu phải lớn hơn 6 kí tự");
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
        return Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    private boolean isLegitP(String password) {
        return password.length() > 6;
    }

    private void onLoginPress() {
        String email = binding.signInEmailEditText.getText().toString();
        String password = binding.signInPasswordEditText.getText().toString();
        if (isLegit(email) && isLegitP(password)) {
            binding.loading.setVisibility(View.VISIBLE);
            viewModel.login(email, password, new OnCompleted<String>() {
                @Override
                public void onFinish(String object) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();

                    binding.loading.setVisibility(View.GONE);
                }

                @Override
                public void onError(String error) {
                    OnCompleted.super.onError(error);
                    GlobalUtil.makeToast(getContext(), error);
                }
            });


        } else
            showLoginFailed("Hãy nhập đúng định dạng email và mật khẩu");

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