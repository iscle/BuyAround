package com.selepdf.hackovid.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selepdf.hackovid.R;
import com.selepdf.hackovid.adapter.CategoryListAdapter;
import com.selepdf.hackovid.adapter.callback.IListAdapter;
import com.selepdf.hackovid.callback.CategoryCallback;
import com.selepdf.hackovid.databinding.FragmentCategoriesBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.model.Category;
import com.selepdf.hackovid.viewmodel.CategoriesViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class CategoriesFragment extends DaggerFragment implements IListAdapter, CategoryCallback {

    private FragmentCategoriesBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private CategoriesViewModel categoriesViewModel;

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

        categoriesViewModel.getAllProductCategories(this);

        binding.categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    @Override
    public void onItemSelected(Object item) {
        // TODO
    }

    @Override
    public void onCategoriesReceived(Category[] categories) {
        binding.categoriesRecyclerView.setAdapter(new CategoryListAdapter(getContext(), this, categories));
    }

    @Override
    public void onFailure(FailureError error) {
        Toast.makeText(getContext(), R.string.internal_error, Toast.LENGTH_LONG).show();
    }
}
