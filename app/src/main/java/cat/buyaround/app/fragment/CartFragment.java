package cat.buyaround.app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.buyaround.app.fragment.CartFragmentDirections;

import cat.buyaround.app.adapter.CartItemsAdapter;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.FragmentCartBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.OrderProduct;
import cat.buyaround.app.viewmodel.CartViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class CartFragment extends DaggerFragment implements IListAdapter {

    private FragmentCartBinding binding;

    @Inject
    protected ViewModelFactory viewModelFactory;
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
        subscribeObservers();
        binding.cartToPayBtn.setOnClickListener(v -> {
            NavDirections action = CartFragmentDirections
                    .actionCartFragmentToPayFragment();
            Navigation.findNavController(v).navigate(action);
        });

        binding.cartClearBtn.setOnClickListener(v -> cartViewModel.clearProductList());

    }

    private void subscribeObservers() {
        cartViewModel.getOrderProducts().observe(getViewLifecycleOwner(), new Observer<List<OrderProduct>>() {
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
        //Navigation.findNavController(binding.getRoot()).navigate(action);
    }
}
