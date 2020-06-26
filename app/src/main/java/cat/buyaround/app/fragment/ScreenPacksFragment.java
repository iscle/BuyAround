package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import cat.buyaround.app.adapter.PackListAdapter;
import cat.buyaround.app.adapter.callback.IAddItemCallback;
import cat.buyaround.app.databinding.FragmentScreenPacksBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.viewmodel.ScreenPacksViewModel;
import dagger.android.support.DaggerFragment;

public class ScreenPacksFragment extends DaggerFragment implements IAddItemCallback {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentScreenPacksBinding binding;
    private ScreenPacksViewModel screenPacksViewModel;
    private RecyclerView packsRv;
    private PackListAdapter packListAdapter;
    private Store store;

    public ScreenPacksFragment(Store store) {
        this.store = store;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScreenPacksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        screenPacksViewModel = new ViewModelProvider(this, viewModelFactory).get(ScreenPacksViewModel.class);

        initViews();

        subscribeObservers();
    }

    private void initViews() {
        packsRv = binding.storePacksRv;
        packListAdapter = new PackListAdapter(getContext(), this);
        packsRv.setAdapter(packListAdapter);
        packsRv.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }

    private void subscribeObservers() {
        screenPacksViewModel.getStorePacks(store).observe(getViewLifecycleOwner(), packs -> {
            if (packs != null && packs.length > 0) {
                packsRv.setVisibility(View.VISIBLE);
                binding.storePacksEmptyView.setVisibility(View.GONE);
            }
            packListAdapter.setPacks(packs);
        });
    }

    @Override
    public void onItemSelected(Object item) {
        // TODO: GO TO PACK FRAGMENT
    }

    @Override
    public void onAddItemTo(Object item) {
        // TODO: ADD PACK TO CART
    }
}
