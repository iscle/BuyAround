package com.selepdf.buyaround.fragment;

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

import com.selepdf.buyaround.MarginItemDecorator;
import com.selepdf.buyaround.adapter.PackListAdapter;
import com.selepdf.buyaround.adapter.ProductListAdapter;
import com.selepdf.buyaround.adapter.StoreListAdapter;
import com.selepdf.buyaround.adapter.callback.IAddItemCallback;
import com.selepdf.buyaround.adapter.callback.IListAdapter;
import com.selepdf.buyaround.databinding.FragmentHomeBinding;
import com.selepdf.buyaround.factory.ViewModelFactory;
import com.selepdf.buyaround.model.OrderProduct;
import com.selepdf.buyaround.model.Pack;
import com.selepdf.buyaround.model.Product;
import com.selepdf.buyaround.model.Store;
import com.selepdf.buyaround.viewmodel.CartViewModel;
import com.selepdf.buyaround.viewmodel.HomeViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment implements IListAdapter, IAddItemCallback {

    private FragmentHomeBinding binding;

    @Inject
    protected ViewModelFactory viewModelFactory;
    @Inject
    protected MarginItemDecorator decorator;
    private HomeViewModel homeViewModel;
    private CartViewModel cartViewModel;

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
        cartViewModel = new ViewModelProvider(this, viewModelFactory).get(CartViewModel.class);

        storesRecyclerView = binding.homeStoresRecyclerView;
        storeListAdapter = new StoreListAdapter(getContext(), this);
        storesRecyclerView.setAdapter(storeListAdapter);
        storesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        storesRecyclerView.addItemDecoration(decorator);

        packsRecyclerView = binding.homePacksRecyclerView;
        packListAdapter = new PackListAdapter(getContext(), this);
        packsRecyclerView.setAdapter(packListAdapter);
        packsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        packsRecyclerView.addItemDecoration(decorator);

        productsRecyclerView = binding.homeProductsRecyclerView;
        productListAdapter = new ProductListAdapter(getContext(), this);
        productsRecyclerView.setAdapter(productListAdapter);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        productsRecyclerView.addItemDecoration(decorator);

        binding.homeSearchHeader.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToSearchFragment();
            Navigation.findNavController(v).navigate(action);
        });

        binding.addStoreTv.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToAddStoreFragment();
            Navigation.findNavController(v).navigate(action);
        });

        binding.addPackTv.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToAddPackFragment();
            Navigation.findNavController(v).navigate(action);
        });

        binding.addProductTv.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToAddProductFragment();
            Navigation.findNavController(v).navigate(action);
        });

        subscribeObservers();
    }

    private void subscribeObservers() {
        homeViewModel.getStores().observe(getViewLifecycleOwner(), stores -> {
            if (stores != null && stores.length > 0) {
                storesRecyclerView.setVisibility(View.VISIBLE);
                binding.storesEmptyView.setVisibility(View.GONE);
            }
            storeListAdapter.setStores(stores);
        });

        homeViewModel.getPacks().observe(getViewLifecycleOwner(), packs -> {
            if (packs != null && packs.length > 0) {
                packsRecyclerView.setVisibility(View.VISIBLE);
                binding.packsEmptyView.setVisibility(View.GONE);
            }
            packListAdapter.setPacks(packs);
        });

        homeViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            if (products != null && products.length > 0) {
                productsRecyclerView.setVisibility(View.VISIBLE);
                binding.productsEmptyView.setVisibility(View.GONE);
            }
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
                NavDirections action = HomeFragmentDirections.actionHomeFragmentToProductFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        }

    }

    @Override
    public void onAddItemTo(Object item) {
        Product product = (Product) item;
        String img = "";
        if (((Product) item).getImages() != null) {
            if (((Product) item).getImages().length > 0) {
                img = ((Product) item).getImages()[0];
            }
        }
        OrderProduct op = new OrderProduct("0", ((Product) item).getName(),
                ((Product) item).getPrice(), img, 1);
        cartViewModel.addProduct(op);
    }
}
