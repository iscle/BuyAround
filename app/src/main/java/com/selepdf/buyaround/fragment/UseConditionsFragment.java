package com.selepdf.buyaround.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.buyaround.databinding.FragmentUseConditionsBinding;
import com.selepdf.buyaround.factory.ViewModelFactory;
import com.selepdf.buyaround.viewmodel.UseConditionsViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class UseConditionsFragment extends DaggerFragment {

    private FragmentUseConditionsBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private UseConditionsViewModel useConditionsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUseConditionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        useConditionsViewModel = new ViewModelProvider(this, viewModelFactory).get(UseConditionsViewModel.class);
    }
}
