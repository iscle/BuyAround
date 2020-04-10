package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.hackovid.databinding.FragmentAddressesBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.viewmodel.AddressesViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class AddressesFragment extends DaggerFragment {

    private FragmentAddressesBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private AddressesViewModel addressesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddressesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addressesViewModel = new ViewModelProvider(this, viewModelFactory).get(AddressesViewModel.class);
    }
}
