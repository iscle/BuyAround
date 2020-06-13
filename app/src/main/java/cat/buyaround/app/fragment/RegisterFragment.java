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

import javax.inject.Inject;

import cat.buyaround.app.R;
import cat.buyaround.app.callback.RegisterCallback;
import cat.buyaround.app.databinding.FragmentRegisterBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.RegisterViewModel;
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
            String name = binding.registerName.getText().toString().trim();
            String email = binding.registerMail.getText().toString().trim();
            String password = binding.registerPassword.getText().toString();
            String passwordVerification = binding.registerPasswordVerification.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(getContext(), R.string.register_empty_name, Toast.LENGTH_LONG).show();
                binding.registerName.requestFocus();

            } else if (email.isEmpty()) {
                Toast.makeText(getContext(), R.string.register_empty_email, Toast.LENGTH_LONG).show();
                binding.registerMail.requestFocus();

            } else if (password.isEmpty()) {
                Toast.makeText(getContext(), R.string.register_empty_password, Toast.LENGTH_LONG).show();
                binding.registerMail.requestFocus();

            } else if (passwordVerification.isEmpty()) {
                Toast.makeText(getContext(), R.string.register_empty_password_verification, Toast.LENGTH_LONG).show();
                binding.registerMail.requestFocus();

            } else if (password.equals(passwordVerification)) {
                if (registerViewModel.isValidEmail(email)) {
                    if (registerViewModel.isValidPassword(password)) {
                        registerViewModel.register(name, email, password, this);
                    } else {
                        Toast.makeText(getContext(), R.string.register_password_not_secure, Toast.LENGTH_LONG).show();
                        binding.registerPassword.requestFocus();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.register_empty_email, Toast.LENGTH_LONG).show();
                    binding.registerMail.requestFocus();
                }
            } else {
                Toast.makeText(getContext(), R.string.register_passwords_dont_match, Toast.LENGTH_LONG).show();
                binding.registerPassword.requestFocus();
            }

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
