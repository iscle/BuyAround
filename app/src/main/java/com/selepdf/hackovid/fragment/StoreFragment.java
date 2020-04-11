package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.selepdf.hackovid.callback.StoreCallback;
import com.selepdf.hackovid.databinding.FragmentStoreBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.viewmodel.StoreViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class StoreFragment extends DaggerFragment implements StoreCallback {

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

        binding.acceptBtn.setOnClickListener(v -> {
            String name = binding.storeEt.getText().toString();
            String description = binding.descriptionEt.getText().toString();

            storeViewModel.addStore(name, description, this);
        });
    }

    @Override
    public void onStoresReceived(Store[] stores) {
        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void onFailure(FailureError error) {
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
    }
}
