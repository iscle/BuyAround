package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import cat.buyaround.app.databinding.FragmentScreenProductsBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.ScreenProductsViewModel;
import dagger.android.support.DaggerFragment;

public class ScreenProductsFragment extends DaggerFragment {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentScreenProductsBinding binding;
    private ScreenProductsViewModel screenProductsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScreenProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
