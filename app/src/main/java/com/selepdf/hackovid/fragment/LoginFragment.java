package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.selepdf.hackovid.R;
import com.selepdf.hackovid.callback.LoginCallback;
import com.selepdf.hackovid.databinding.FragmentLoginBinding;
import com.selepdf.hackovid.model.UserType;

import dagger.android.support.DaggerFragment;

public class LoginFragment extends DaggerFragment implements LoginCallback {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.login_login_to_register).setOnClickListener(v -> {
            NavDirections action = LoginFragmentDirections
                    .actionLoginFragmentToRegisterFragment();
            Navigation.findNavController(v).navigate(action);
        });

        view.findViewById(R.id.login_btn_login).setOnClickListener(v -> {
            // TODO: API CALL TO LOG IN
        });
    }

    @Override
    public void onSuccess(UserType userType) {
        // TODO: GO TO MAIN FRAGMENT
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
