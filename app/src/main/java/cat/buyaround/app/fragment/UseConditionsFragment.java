package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import cat.buyaround.app.databinding.FragmentUseConditionsBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.UseConditionsViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class UseConditionsFragment extends DaggerFragment {

    private FragmentUseConditionsBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private UseConditionsViewModel useConditionsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUseConditionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        useConditionsViewModel = new ViewModelProvider(this, viewModelFactory).get(UseConditionsViewModel.class);
    }
}
