package com.selepdf.buyaround.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selepdf.buyaround.MarginItemDecorator;
import com.selepdf.buyaround.adapter.PackListAdapter;
import com.selepdf.buyaround.adapter.ProductListAdapter;
import com.selepdf.buyaround.adapter.StoreListAdapter;
import com.selepdf.buyaround.adapter.callback.IListAdapter;
import com.selepdf.buyaround.databinding.FragmentFavouritesBinding;
import com.selepdf.buyaround.factory.ViewModelFactory;
import com.selepdf.buyaround.model.Pack;
import com.selepdf.buyaround.model.Store;
import com.selepdf.buyaround.viewmodel.FavouritesViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FavouritesFragment extends DaggerFragment implements IListAdapter {

    private FragmentFavouritesBinding binding;

    @Inject
    protected ViewModelFactory viewModelFactory;
    @Inject
    protected MarginItemDecorator decorator;
    private FavouritesViewModel favouritesViewModel;

    private RecyclerView storesRecyclerView;
    private StoreListAdapter storeListAdapter;
    private RecyclerView packsRecyclerView;
    private PackListAdapter packListAdapter;
    private RecyclerView productsRecyclerView;
    private ProductListAdapter productListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favouritesViewModel = new ViewModelProvider(this, viewModelFactory).get(FavouritesViewModel.class);

        storesRecyclerView = binding.favStoresRecyclerView;
        storeListAdapter = new StoreListAdapter(getContext(), this);
        storesRecyclerView.setAdapter(storeListAdapter);
        storesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        storesRecyclerView.addItemDecoration(decorator);

        packsRecyclerView = binding.favPacksRecyclerView;
        packListAdapter = new PackListAdapter(getContext(), this);
        packsRecyclerView.setAdapter(packListAdapter);
        packsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        packsRecyclerView.addItemDecoration(decorator);

        productsRecyclerView = binding.favProductsRecyclerView;
        productListAdapter = new ProductListAdapter(getContext(), this);
        productsRecyclerView.setAdapter(productListAdapter);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        productsRecyclerView.addItemDecoration(decorator);

        subscribeObservers();
    }

    private void subscribeObservers() {
        favouritesViewModel.getFavouriteStores().observe(getViewLifecycleOwner(), stores -> {
            storeListAdapter.setStores(stores);
        });

        favouritesViewModel.getFavouritePacks().observe(getViewLifecycleOwner(), packs -> {
            packListAdapter.setPacks(packs);
        });

        favouritesViewModel.getFavouriteProducts().observe(getViewLifecycleOwner(), products -> {
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
