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

import com.selepdf.buyaround.callback.CategoryCallback;
import com.selepdf.buyaround.callback.StoreCallback;
import com.selepdf.buyaround.databinding.FragmentAddStoreBinding;
import com.selepdf.buyaround.factory.ViewModelFactory;
import com.selepdf.buyaround.model.Category;
import com.selepdf.buyaround.model.Store;
import com.selepdf.buyaround.viewmodel.AddStoreViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class AddStoreFragment extends DaggerFragment implements StoreCallback, CategoryCallback {

    private FragmentAddStoreBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private AddStoreViewModel addStoreViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddStoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addStoreViewModel = new ViewModelProvider(this, viewModelFactory).get(AddStoreViewModel.class);

        addStoreViewModel.getStoreCategories(this);

        binding.acceptBtn.setOnClickListener(v -> {
            String name = binding.storeEt.getText().toString();
            String description = binding.descriptionEt.getText().toString();

            addStoreViewModel.addStore(name, description, this);
        });
    }

    @Override
    public void onStoresReceived(Store[] stores) {
        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void onFailure(FailureError error) {
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoriesReceived(Category[] categories) {
        List<String> spinnerArray = new ArrayList<>();
        for (Category category : categories) {
            spinnerArray.add(category.getName());
        }

        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.categorySpin.setAdapter(adapter);
    }
}
