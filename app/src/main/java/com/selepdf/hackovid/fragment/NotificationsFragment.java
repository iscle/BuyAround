package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.selepdf.hackovid.databinding.FragmentNotificationsBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.viewmodel.NotificationsViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class NotificationsFragment extends DaggerFragment {
    private FragmentNotificationsBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private NotificationsViewModel notificationsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
