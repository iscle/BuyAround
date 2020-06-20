package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import cat.buyaround.app.databinding.FragmentAddAddressBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.AddAddressViewModel;
import dagger.android.support.DaggerFragment;

public class AddAddressFragment extends DaggerFragment {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentAddAddressBinding binding;
    private AddAddressViewModel addAddressViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddAddressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addAddressViewModel = new ViewModelProvider(this, viewModelFactory).get(AddAddressViewModel.class);
    }
}
