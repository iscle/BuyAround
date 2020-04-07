package com.selepdf.hackovid.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.selepdf.hackovid.repository.HackovidRepository;
import com.selepdf.hackovid.viewmodel.LoginViewModel;

import javax.inject.Inject;

import dagger.Module;

@Module
public class ViewModelFactory implements ViewModelProvider.Factory {

    private HackovidRepository hackovidRepository;

    @Inject
    public ViewModelFactory(HackovidRepository hackovidRepository) {
        this.hackovidRepository = hackovidRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(hackovidRepository);
        }

        throw new IllegalArgumentException("Unknown class!");
    }
}
