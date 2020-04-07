package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.selepdf.hackovid.R;
import com.selepdf.hackovid.databinding.FragmentLoginBinding;

import dagger.android.support.DaggerFragment;

public class LoginFragment extends DaggerFragment {

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

        view.findViewById(R.id.button).setOnClickListener(v -> {
            NavDirections action = LoginFragmentDirections
                    .actionLoginFragmentToRegisterFragment();
            Navigation.findNavController(v).navigate(action);
        });
    }
}
