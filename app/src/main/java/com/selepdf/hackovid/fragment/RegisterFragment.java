package com.selepdf.hackovid.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.selepdf.hackovid.R;
import com.selepdf.hackovid.callback.RegisterCallback;
import com.selepdf.hackovid.databinding.FragmentRegisterBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.viewmodel.RegisterViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class RegisterFragment extends DaggerFragment implements RegisterCallback {

    private FragmentRegisterBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private RegisterViewModel registerViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerViewModel = new ViewModelProvider(this, viewModelFactory).get(RegisterViewModel.class);

        binding.registerRegisterToLogin.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

        binding.registerBtnRegister.setOnClickListener(v -> {
            String name = binding.registerUser.getText().toString();
            String email = binding.registerMail.getText().toString();
            String password = binding.registerPassword.getText().toString();

            registerViewModel.register(email, password, this);
        });
    }

    @Override
    public void onSuccess() {
        NavHostFragment.findNavController(this).popBackStack();
    }

    @Override
    public void onFailure(RegisterError registerError) {
        switch (registerError) {
            case NETWORK_ERROR:
                Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_LONG).show();
                break;
            case INTERNAL_ERROR:
                Toast.makeText(getContext(), R.string.internal_error, Toast.LENGTH_LONG).show();
                break;
            case ALREADY_EXISTS:
                Toast.makeText(getContext(), R.string.already_exists, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
