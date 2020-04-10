package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.hackovid.databinding.FragmentPersonalInfoBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.viewmodel.PersonalInfoViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PersonalInfoFragment extends DaggerFragment {

    private FragmentPersonalInfoBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private PersonalInfoViewModel personalInfoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        personalInfoViewModel = new ViewModelProvider(this, viewModelFactory).get(PersonalInfoViewModel.class);
    }
}
