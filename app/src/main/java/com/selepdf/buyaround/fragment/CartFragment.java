package com.selepdf.buyaround.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selepdf.buyaround.R;
import com.selepdf.buyaround.adapter.CartItemsAdapter;
import com.selepdf.buyaround.adapter.callback.IListAdapter;
import com.selepdf.buyaround.databinding.FragmentCartBinding;
import com.selepdf.buyaround.factory.ViewModelFactory;
import com.selepdf.buyaround.model.Order;
import com.selepdf.buyaround.model.OrderProduct;
import com.selepdf.buyaround.model.Product;
import com.selepdf.buyaround.viewmodel.CartViewModel;
import com.selepdf.buyaround.viewmodel.HomeViewModel;

import java.util.List;

import javax.inject.Inject;

public class CartFragment extends Fragment implements IListAdapter {

    private FragmentCartBinding binding;

    @Inject
    private ViewModelFactory viewModelFactory;
    private CartViewModel cartViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartViewModel = new ViewModelProvider(this, viewModelFactory).get(CartViewModel.class);

        binding.cartItemsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.cartToPayBtn.setOnClickListener(v -> {
            NavDirections action = CartFragmentDirections
                    .actionCartFragmentToPayFragment();
            Navigation.findNavController(v).navigate(action);
        });

        binding.cartClearBtn.setOnClickListener(v -> cartViewModel.clearProductList());

    }

    private void subscribeObservers() {
        cartViewModel.getOrderProducts().observe(this, new Observer<List<OrderProduct>>() {
            @Override
            public void onChanged(List<OrderProduct> products) {
                binding.cartItemsRecyclerview.setAdapter(new CartItemsAdapter(products, CartFragment.this));
                binding.cartTotalCost.setText(Float.toString(calculateTotalCost(products)));
            }
        });
    }

    private float calculateTotalCost(List<OrderProduct> products) {
        float res = 0;
        for (OrderProduct item : products) {
            res += item.getPrice() * item.getQuantity();
        }
        return res;
    }

    @Override
    public void onItemSelected(Object item) {
        NavDirections action = CartFragmentDirections
                .actionCartFragmentToProductFragment();
        Navigation.findNavController(binding.getRoot()).navigate(action);
    }
}
