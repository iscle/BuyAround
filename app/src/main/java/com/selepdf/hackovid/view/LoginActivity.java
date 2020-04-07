package com.selepdf.hackovid.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.selepdf.hackovid.R;
import com.selepdf.hackovid.callback.LoginCallback;
import com.selepdf.hackovid.databinding.ActivityLoginBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.model.UserType;
import com.selepdf.hackovid.viewmodel.LoginViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity implements LoginCallback {
    private static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    @Inject
    protected ViewModelFactory viewModelFactory;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        loginViewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);

        // Handle login on button press
        binding.login.setOnClickListener(v -> {
            String username = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            loginViewModel.login(username, password, LoginActivity.this);
        });
    }

    @Override
    public void onSuccess(UserType userType) {
        Log.d(TAG, "onSuccess: Logged in as " + userType);
        switch (userType) {
            case CUSTOMER:

                break;
            case STORE:

                break;
            case COURIER:

                break;
        }
    }

    @Override
    public void onFailure(LoginError loginError) {
        switch (loginError) {
            case WRONG_PASSWORD:
                Toast.makeText(this, R.string.wrong_password, Toast.LENGTH_SHORT).show();
                break;
            case INTERNAL_ERROR:
                Toast.makeText(this, R.string.internal_error, Toast.LENGTH_SHORT).show();
                break;
            case NETWORK_ERROR:
                Toast.makeText(this, R.string.network_error, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
