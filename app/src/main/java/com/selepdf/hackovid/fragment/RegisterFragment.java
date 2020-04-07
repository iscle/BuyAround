package com.selepdf.hackovid.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selepdf.hackovid.R;
import com.selepdf.hackovid.databinding.FragmentRegisterBinding;

import dagger.android.support.DaggerFragment;

public class RegisterFragment extends DaggerFragment {

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
