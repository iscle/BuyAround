package cat.buyaround.app.fragment;

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

import cat.buyaround.app.callback.CategoryCallback;
import cat.buyaround.app.callback.StoreCallback;
import cat.buyaround.app.databinding.FragmentAddStoreBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.Category;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.network.model.SimpleResponse;
import cat.buyaround.app.viewmodel.AddStoreViewModel;

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
    public void onFailure(SimpleResponse.Status error) {
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
