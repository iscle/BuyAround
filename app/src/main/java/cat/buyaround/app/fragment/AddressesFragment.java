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

import cat.buyaround.app.adapter.AddressAdapter;
import cat.buyaround.app.databinding.FragmentAddressesBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.AddressesViewModel;
import dagger.android.support.DaggerFragment;

public class AddressesFragment extends DaggerFragment {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentAddressesBinding binding;
    private AddressesViewModel addressesViewModel;
    private AddressAdapter addressAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddressesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addressesViewModel = new ViewModelProvider(this, viewModelFactory).get(AddressesViewModel.class);

        binding.addressRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        addressAdapter = new AddressAdapter();
        binding.addressRecycler.setAdapter(addressAdapter);
        binding.addressRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        updateAddresses();
    }

    private void updateAddresses() {
        //xd
    }
}
