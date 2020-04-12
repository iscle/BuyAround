package com.selepdf.buyaround.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.buyaround.databinding.FragmentPaymentBinding;
import com.selepdf.buyaround.factory.ViewModelFactory;
import com.selepdf.buyaround.viewmodel.PaymentViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PaymentFragment extends DaggerFragment {

    private FragmentPaymentBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private PaymentViewModel paymentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        paymentViewModel = new ViewModelProvider(this, viewModelFactory).get(PaymentViewModel.class);
    }
}
