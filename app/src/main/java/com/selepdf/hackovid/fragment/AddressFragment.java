package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.hackovid.databinding.FragmentAddressBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.viewmodel.AddressViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class AddressFragment extends DaggerFragment {

    private FragmentAddressBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private AddressViewModel addressViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addressViewModel = new ViewModelProvider(this, viewModelFactory).get(AddressViewModel.class);
    }
}
