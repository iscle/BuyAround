package cat.buyaround.app.fragment;

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

import javax.inject.Inject;

import cat.buyaround.app.adapter.PackListAdapter;
import cat.buyaround.app.adapter.ProductListAdapter;
import cat.buyaround.app.adapter.StoreListAdapter;
import cat.buyaround.app.adapter.callback.IAddItemCallback;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.FragmentHomeBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.OrderProduct;
import cat.buyaround.app.model.Pack;
import cat.buyaround.app.model.Product;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.utils.MarginItemDecorator;
import cat.buyaround.app.viewmodel.CartViewModel;
import cat.buyaround.app.viewmodel.HomeViewModel;
import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment implements IListAdapter, IAddItemCallback {

    @Inject
    protected ViewModelFactory viewModelFactory;
    @Inject
    protected MarginItemDecorator decorator;
    private FragmentHomeBinding binding;
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

        initViews();

        subscribeObservers();
    }

    private void initViews() {
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

        binding.searchEt.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToSearchFragment();
            Navigation.findNavController(v).navigate(action);
        });
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
            HomeFragmentDirections.ActionHomeFragmentToStoreFragment action =
                    HomeFragmentDirections.actionHomeFragmentToStoreFragment();
            action.setStore((Store) item);

            Navigation.findNavController(binding.getRoot()).navigate(action);

        } else {

            if (item instanceof Pack) {

            } else {
                HomeFragmentDirections.ActionHomeFragmentToProductFragment action =
                        HomeFragmentDirections.actionHomeFragmentToProductFragment();
                action.setProduct((Product) item);

                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        }
    }

    @Override
    public void onAddItemTo(Object item) {
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
