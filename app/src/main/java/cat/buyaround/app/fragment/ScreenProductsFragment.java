package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import cat.buyaround.app.adapter.ProductListAdapter;
import cat.buyaround.app.adapter.callback.IAddItemCallback;
import cat.buyaround.app.databinding.FragmentScreenProductsBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.viewmodel.ScreenProductsViewModel;
import dagger.android.support.DaggerFragment;

public class ScreenProductsFragment extends DaggerFragment implements IAddItemCallback {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentScreenProductsBinding binding;
    private ScreenProductsViewModel screenProductsViewModel;
    private RecyclerView productsRv;
    private ProductListAdapter productListAdapter;
    private Store store;

    public ScreenProductsFragment(Store store) {
        this.store = store;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScreenProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        screenProductsViewModel = new ViewModelProvider(this, viewModelFactory).get(ScreenProductsViewModel.class);

        initViews();

        subscribeObservers();
    }

    private void initViews() {
        productsRv = binding.storeProductsRv;
        productListAdapter = new ProductListAdapter(getContext(), this);
        productsRv.setAdapter(productListAdapter);
        productsRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }

    private void subscribeObservers() {
        screenProductsViewModel.getStoreProducts(store).observe(getViewLifecycleOwner(), products -> {
            if (products != null && products.length > 0) {
                productsRv.setVisibility(View.VISIBLE);
                binding.storeProductsEmptyView.setVisibility(View.GONE);
            }
            productListAdapter.setProducts(products);
        });
    }

    @Override
    public void onItemSelected(Object item) {
        // TODO: GO TO PRODUCT FRAGMENT
    }

    @Override
    public void onAddItemTo(Object item) {
        // TODO: ADD PRODUCT TO CART
    }
}
