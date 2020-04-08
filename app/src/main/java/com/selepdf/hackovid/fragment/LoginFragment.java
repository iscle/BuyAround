package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.selepdf.hackovid.R;
import com.selepdf.hackovid.callback.LoginCallback;
import com.selepdf.hackovid.databinding.FragmentLoginBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.viewmodel.LoginViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class LoginFragment extends DaggerFragment implements LoginCallback {

    private FragmentLoginBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginViewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);

        binding.loginLoginToRegister.setOnClickListener(v -> {
            NavDirections action = LoginFragmentDirections
                    .actionLoginFragmentToRegisterFragment();
            Navigation.findNavController(v).navigate(action);
        });

        binding.loginBtnLogin.setOnClickListener(v -> {
            String username = binding.loginUser.getText().toString();
            String password = binding.loginPassword.getText().toString();
            loginViewModel.login(username, password, this);
        });
    }

    @Override
    public void onSuccess(String token) {
        NavHostFragment.findNavController(this).popBackStack();
    }

    @Override
    public void onFailure(LoginError loginError) {
        switch (loginError) {
            case NETWORK_ERROR:
                Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_LONG).show();
                break;
            case INTERNAL_ERROR:
                Toast.makeText(getContext(), R.string.internal_error, Toast.LENGTH_LONG).show();
                break;
            case WRONG_PASSWORD:
                Toast.makeText(getContext(), R.string.wrong_password, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
