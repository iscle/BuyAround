package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import cat.buyaround.app.databinding.FragmentScreenPacksBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.ScreenPacksViewModel;
import dagger.android.support.DaggerFragment;

public class ScreenPacksFragment extends DaggerFragment {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentScreenPacksBinding binding;
    private ScreenPacksViewModel screenPacksViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScreenPacksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
