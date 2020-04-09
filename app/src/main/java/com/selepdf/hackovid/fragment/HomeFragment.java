package com.selepdf.hackovid.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selepdf.hackovid.adapter.PackListAdapter;
import com.selepdf.hackovid.adapter.ProductListAdapter;
import com.selepdf.hackovid.adapter.StoreListAdapter;
import com.selepdf.hackovid.adapter.callback.IListAdapter;
import com.selepdf.hackovid.databinding.FragmentHomeBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.model.GeneralItem;
import com.selepdf.hackovid.model.Pack;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.viewmodel.HomeViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment implements IListAdapter {

    private FragmentHomeBinding binding;

    @Inject
    protected ViewModelFactory viewModelFactory;
    private HomeViewModel homeViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = new ViewModelProvider(this, viewModelFactory).get(HomeViewModel.class);

        binding.homeShopsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.homePacksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.homeProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        subscribeObservers();


    }

    private void subscribeObservers() {
        homeViewModel.getStores().observe(getViewLifecycleOwner(), new Observer<List<Store>>() {
            @Override
            public void onChanged(@NonNull List<Store> stores) {
                binding.homeShopsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), stores.size()/2));
                binding.homeShopsRecyclerView.setAdapter(new StoreListAdapter(getContext(), HomeFragment.this, stores));

            }
        });

        homeViewModel.getPacks().observe(getViewLifecycleOwner(), new Observer<List<Pack>>() {
            @Override
            public void onChanged(@NonNull List<Pack> packs) {
                binding.homePacksRecyclerView.setAdapter(new PackListAdapter(getContext(), HomeFragment.this, packs));
            }
        });

        homeViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(@NonNull List<Product> products) {
                binding.homeProductsRecyclerView.setAdapter(new ProductListAdapter(getContext(), HomeFragment.this, products));
            }
        });
    }

    /**********************************************************************************************
     *   *   *   *   *   *   *   *   AdapterCallback   *   *   *   *   *   *   *   *   *
     **********************************************************************************************/

    @Override
    public void onItemSelected(Object item) {
        if (item instanceof Store) {

        } else {

            if (item instanceof Pack) {

            } else {

            }
        }

    }
}
