package com.selepdf.hackovid.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.selepdf.hackovid.R;
import com.selepdf.hackovid.callback.RegisterCallback;
import com.selepdf.hackovid.databinding.FragmentRegisterBinding;

import dagger.android.support.DaggerFragment;

public class RegisterFragment extends DaggerFragment implements RegisterCallback {

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.register_register_to_login).setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

        view.findViewById(R.id.register_btn_register).setOnClickListener(v -> {
            // TODO: API CALL TO REGISTER
        });
    }

    @Override
    public void onSuccess() {
        // TODO: LOG IN USER AND GO TO MAIN FRAGMENT
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
