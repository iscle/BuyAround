package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import cat.buyaround.app.databinding.FragmentProductBinding;

import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.ProductViewModel;
import dagger.android.support.DaggerFragment;

public class ProductFragment extends DaggerFragment {

    private FragmentProductBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private ProductViewModel productViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productViewModel = new ViewModelProvider(this, viewModelFactory).get(ProductViewModel.class);

        if (getArguments() != null) {
            productViewModel.setProduct(ProductFragmentArgs.fromBundle(getArguments()).getProduct());
        }

        initViews();
    }

    private void initViews() {
        binding.productName.setText(productViewModel.getProductName());

        binding.productDescription.setText(productViewModel.getProductDescription());

        binding.productPrice.setText(String.valueOf(productViewModel.getProductPrice()));

        binding.productPoints.setText(String.valueOf(productViewModel.getProductPoints()));

        binding.productAddBtn.setOnClickListener(v -> {
            int productQuantity = Integer.parseInt(binding.productQuantity.getText().toString());
            binding.productQuantity.setText(String.valueOf(productQuantity + 1));
        });

        binding.productSubstractBtn.setOnClickListener(v -> {
            int productQuantity = Integer.parseInt(binding.productQuantity.getText().toString());
            if (productQuantity > 0) {
                binding.productQuantity.setText(String.valueOf(productQuantity - 1));
            }
        });

        // TODO: UNCOMMENT
        // binding.productUnit.setText(productViewModel.getProductUnit());

        binding.productAddCartBtn.setOnClickListener(v -> {
            // TODO: ADD TO CART API CALL + TOAST + ADD TEXT "X ADDED TO CART"
        });
    }
}
