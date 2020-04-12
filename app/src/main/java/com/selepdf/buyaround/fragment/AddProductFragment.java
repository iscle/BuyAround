package com.selepdf.buyaround.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.selepdf.buyaround.R;
import com.selepdf.buyaround.callback.CategoryCallback;
import com.selepdf.buyaround.callback.ProductCallback;
import com.selepdf.buyaround.databinding.FragmentAddProductBinding;
import com.selepdf.buyaround.factory.ViewModelFactory;
import com.selepdf.buyaround.model.Category;
import com.selepdf.buyaround.model.Product;
import com.selepdf.buyaround.viewmodel.AddProductViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class AddProductFragment extends DaggerFragment implements ProductCallback, CategoryCallback {

    private FragmentAddProductBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private AddProductViewModel addProductViewModel;
    private ArrayList<String> addedImages = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addProductViewModel = new ViewModelProvider(this, viewModelFactory).get(AddProductViewModel.class);

        addProductViewModel.getProductCategories(this);

        binding.productAddImgsBtn.setOnClickListener(v -> {
            addedImages.add(binding.imagesSpin.getSelectedItem().toString());
        });

        binding.cancelBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

        binding.acceptBtn.setOnClickListener(v -> {
            // TODO: FINISH THIS
            /*Product product = new Product(binding.productEt.toString(),
                    binding.descriptionEt.toString(),
                    null,
                    Float.parseFloat(binding.priceEt.toString().trim()),
                    -1,
                    addedImages.toArray(new String[addedImages.size()]),
                    new ProductCategory(binding.categorySpin.getSelectedItem().toString()),
                    null);*/
            Product product = new Product(binding.productAddName.getText().toString(), binding.productAddDescription.getText().toString(), binding.productAddPrice.getText().toString());

            addProductViewModel.addProduct(product, this);
        });
    }

    @Override
    public void onFailure(FailureError error) {
        // TODO: TOAST WITH THE ERROR
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProductsReceived(Product[] products) {
        if (products.length == 1) {
            Navigation.findNavController(getView()).popBackStack();
        }
    }

    @Override
    public void onCategoriesReceived(Category[] categories) {
        List<String> spinnerArray = new ArrayList<>();
        for (int i = 0; i < categories.length; ++i) {
            spinnerArray.add(categories[i].getName());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.productAddCategory.setAdapter(adapter);
    }
}
