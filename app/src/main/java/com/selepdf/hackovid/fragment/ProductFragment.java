package com.selepdf.hackovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.selepdf.hackovid.callback.ProductCallback;
import com.selepdf.hackovid.databinding.FragmentProductBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.ProductCategory;
import com.selepdf.hackovid.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProductFragment extends DaggerFragment implements ProductCallback {

    private FragmentProductBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private ProductViewModel productViewModel;
    private ArrayList<String> addedImages = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productViewModel = new ViewModelProvider(this, viewModelFactory).get(ProductViewModel.class);

        //TODO: FILL SPINNERS

        binding.addImageBtn.setOnClickListener(v -> {
            addedImages.add(binding.imagesSpin.getSelectedItem().toString());
        });

        binding.cancelBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

        binding.acceptBtn.setOnClickListener(v -> {
            // TODO: FINISH THIS
            Product product = new Product(binding.productEt.toString(),
                    binding.descriptionEt.toString(),
                    null,
                    Float.parseFloat(binding.priceEt.toString().trim()),
                    -1,
                    addedImages.toArray(new String[addedImages.size()]),
                    new ProductCategory(binding.categorySpin.getSelectedItem().toString()),
                    null);

            productViewModel.addProduct(product, this);
        });
    }

    @Override
    public void onProductsReceived(List<Product> products) {
        // UNUSED
    }

    @Override
    public void onFailure(FailureError error) {
        // TODO: TOAST WITH THE ERROR
    }
}
