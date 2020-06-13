package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import cat.buyaround.app.R;
import cat.buyaround.app.callback.RegisterCallback;
import cat.buyaround.app.databinding.FragmentRegisterBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.RegisterViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class RegisterFragment extends DaggerFragment implements RegisterCallback {

    private FragmentRegisterBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private RegisterViewModel registerViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerViewModel = new ViewModelProvider(this, viewModelFactory).get(RegisterViewModel.class);

        binding.registerToLogin.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

        binding.registerBtn.setOnClickListener(v -> {
            String name = binding.registerName.getText().toString();
            String email = binding.registerMail.getText().toString();
            String password = binding.registerPassword.getText().toString();

            registerViewModel.register(name, email, password, this);
        });
    }

    @Override
    public void onSuccess() {
        NavHostFragment.findNavController(this).popBackStack();
    }

    @Override
    public void onFailure(RegisterError registerError) {
        switch (registerError) {
            case NETWORK_ERROR:
                Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_LONG).show();
                break;
            case INTERNAL_ERROR:
                Toast.makeText(getContext(), R.string.internal_error, Toast.LENGTH_LONG).show();
                break;
            case EXISTING_EMAIL:
                Toast.makeText(getContext(), R.string.existing_email, Toast.LENGTH_LONG).show();
                break;
            case WEAK_PASSWORD:
                Toast.makeText(getContext(), R.string.weak_password, Toast.LENGTH_LONG).show();
                break;
            case MISSING_PARAMETERS:
                Toast.makeText(getContext(), R.string.missing_parameters, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
