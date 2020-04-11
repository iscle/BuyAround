package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selepdf.hackovid.adapter.PackListAdapter;
import com.selepdf.hackovid.adapter.ProductListAdapter;
import com.selepdf.hackovid.adapter.StoreListAdapter;
import com.selepdf.hackovid.adapter.callback.IListAdapter;
import com.selepdf.hackovid.databinding.FragmentHomeBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.model.Pack;
import com.selepdf.hackovid.model.Store;
import com.selepdf.hackovid.viewmodel.HomeViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment implements IListAdapter {

    private FragmentHomeBinding binding;

    @Inject
    protected ViewModelFactory viewModelFactory;
    private HomeViewModel homeViewModel;

    private RecyclerView storesRecyclerView;
    private StoreListAdapter storeListAdapter;
    private RecyclerView packsRecyclerView;
    private PackListAdapter packListAdapter;
    private RecyclerView productsRecyclerView;
    private ProductListAdapter productListAdapter;

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

        storesRecyclerView = binding.homeStoresRecyclerView;
        storeListAdapter = new StoreListAdapter(getContext(), this);
        storesRecyclerView.setAdapter(storeListAdapter);
        storesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        packsRecyclerView = binding.homePacksRecyclerView;
        packListAdapter = new PackListAdapter(getContext(), this);
        packsRecyclerView.setAdapter(packListAdapter);
        packsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        productsRecyclerView = binding.homeProductsRecyclerView;
        productListAdapter = new ProductListAdapter(getContext(), this);
        productsRecyclerView.setAdapter(productListAdapter);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        binding.textView2.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToStoreFragment();
            Navigation.findNavController(getView()).navigate(action);
        });

        binding.textView4.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToProductFragment();
            Navigation.findNavController(getView()).navigate(action);
        });

        subscribeObservers();
    }

    private void subscribeObservers() {
        homeViewModel.getStores().observe(getViewLifecycleOwner(), stores -> {
            storeListAdapter.setStores(stores);
        });

        homeViewModel.getPacks().observe(getViewLifecycleOwner(), packs -> {
            packListAdapter.setPacks(packs);
        });

        homeViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            productListAdapter.setProducts(products);
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
