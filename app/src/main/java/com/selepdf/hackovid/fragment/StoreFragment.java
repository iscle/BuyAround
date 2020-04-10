package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.hackovid.databinding.FragmentStoreBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.viewmodel.StoreViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class StoreFragment extends DaggerFragment {

    private FragmentStoreBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private StoreViewModel storeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storeViewModel = new ViewModelProvider(this, viewModelFactory).get(StoreViewModel.class);
    }
}
