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

import cat.buyaround.app.adapter.CartItemListAdapter;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.FragmentCartBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.Product;
import cat.buyaround.app.viewmodel.CartViewModel;
import dagger.android.support.DaggerFragment;

public class CartFragment extends DaggerFragment implements IListAdapter {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentCartBinding binding;
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

        binding.cartItemsRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));

        binding.cartToPayBtn.setOnClickListener(v -> {
            NavDirections action = CartFragmentDirections
                    .actionCartFragmentToPayFragment();
            Navigation.findNavController(v).navigate(action);
        });

        binding.cartClearBtn.setOnClickListener(v -> {
            cartViewModel.clearProductList();
            NavDirections action = CartFragmentDirections
                    .actionCartFragmentToHomeFragment();
            Navigation.findNavController(v).navigate(action);
        });

        subscribeObservers();
    }

    private void subscribeObservers() {
        cartViewModel.getOrderProducts().observe(getViewLifecycleOwner(), products -> {
            binding.cartItemsRecyclerview.setAdapter(new CartItemListAdapter(products, CartFragment.this));
            binding.cartTotalCost.setText(String.valueOf(cartViewModel.getTotalCost()));
        });
    }

    @Override
    public void onItemSelected(Object item) {
        CartFragmentDirections.ActionCartFragmentToProductFragment action =
                CartFragmentDirections.actionCartFragmentToProductFragment();
        action.setProduct((Product) item);
        Navigation.findNavController(binding.getRoot()).navigate(action);
    }
}
