package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cat.buyaround.app.utils.MarginItemDecorator;
import cat.buyaround.app.adapter.PackListAdapter;
import cat.buyaround.app.adapter.ProductListAdapter;
import cat.buyaround.app.adapter.StoreListAdapter;
import cat.buyaround.app.adapter.callback.IAddItemCallback;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.FragmentFavouritesBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.Pack;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.viewmodel.FavouritesViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FavouritesFragment extends DaggerFragment implements IListAdapter, IAddItemCallback {

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
            if (stores != null && stores.length > 0) {
                storesRecyclerView.setVisibility(View.VISIBLE);
                binding.storesEmptyView.setVisibility(View.GONE);
            }
            storeListAdapter.setStores(stores);
        });

        favouritesViewModel.getFavouritePacks().observe(getViewLifecycleOwner(), packs -> {
            if (packs != null && packs.length > 0) {
                packsRecyclerView.setVisibility(View.VISIBLE);
                binding.packsEmptyView.setVisibility(View.GONE);
            }
            packListAdapter.setPacks(packs);
        });

        favouritesViewModel.getFavouriteProducts().observe(getViewLifecycleOwner(), products -> {
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

            }
        }

    }

    @Override
    public void onAddItemTo(Object item) {

    }
}
