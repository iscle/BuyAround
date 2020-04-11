package com.selepdf.hackovid.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selepdf.hackovid.adapter.CategoryListAdapter;
import com.selepdf.hackovid.adapter.callback.IListAdapter;
import com.selepdf.hackovid.databinding.FragmentCategoriesBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.viewmodel.CategoriesViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class CategoriesFragment extends DaggerFragment implements IListAdapter {

    private FragmentCategoriesBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private CategoriesViewModel categoriesViewModel;

    CategoryListAdapter categoryListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoriesViewModel = new ViewModelProvider(this, viewModelFactory).get(CategoriesViewModel.class);

        categoriesViewModel.getAllProductCategories();

        categoryListAdapter = new CategoryListAdapter(getContext(), this);
        binding.categoriesRecyclerView.setAdapter(categoryListAdapter);
        binding.categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        subscribeObserver();
    }

    private void subscribeObserver() {
        categoriesViewModel.getAllProductCategories().observe(getViewLifecycleOwner(), categories -> {
            categoryListAdapter.setCategories(categories);
        });
    }


    @Override
    public void onItemSelected(Object item) {
        // TODO
    }
}
