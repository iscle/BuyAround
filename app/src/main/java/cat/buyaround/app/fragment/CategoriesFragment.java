package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import cat.buyaround.app.adapter.CategoryListAdapter;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.FragmentCategoriesBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.Category;
import cat.buyaround.app.viewmodel.CategoriesViewModel;
import dagger.android.support.DaggerFragment;

public class CategoriesFragment extends DaggerFragment implements IListAdapter {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentCategoriesBinding binding;
    private CategoriesViewModel categoriesViewModel;

    private RecyclerView recyclerView;
    private CategoryListAdapter categoryListAdapter;

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

        recyclerView = binding.categoriesRecyclerView;
        categoryListAdapter = new CategoryListAdapter(requireContext(), this);
        recyclerView.setAdapter(categoryListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        subscribeObserver();
    }

    private void subscribeObserver() {
        categoriesViewModel.getAllStoreCategories().observe(getViewLifecycleOwner(), categories -> {
            categoryListAdapter.setCategories(categories);
        });
    }


    @Override
    public void onItemSelected(Object item) {
        if (item instanceof Category) {
            // TODO: GO TO SEARCH FRAGMENT AND MAKE AN API CALL LOOKING FOR SPECIFIED CATEGORY
        }
    }
}
