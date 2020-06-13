package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.ContentListAdapter;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.FragmentAccountBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.AccountViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class AccountFragment extends DaggerFragment implements IListAdapter {

    private FragmentAccountBinding binding;
    private final List<String> mContents = new ArrayList<>(
            Arrays.asList("Personal Info", "Addresses", "Payment", "Use conditions"));

    @Inject
    protected ViewModelFactory viewModelFactory;
    private AccountViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this, viewModelFactory).get(AccountViewModel.class);
        subscribeObservers();
        binding.accountRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        binding.accountRecyclerView.setAdapter(new ContentListAdapter(this, mContents));

        binding.profileJobReference.setOnClickListener(v -> {
            // TODO: DELETE
        });

    }

    private void subscribeObservers() {
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            binding.profileUsername.setText(user.getName());
            binding.profileEmail.setText(user.getEmail());
            Glide.with(AccountFragment.this)
                    .asBitmap()
                    .placeholder(R.drawable.ic_thumbnail)
                    .load(user.getProfilePicture())
                    .into(binding.profileThumbnail);
        });
    }


    /**********************************************************************************************
     *   *   *   *   *   *   *   *   AdapterCallback   *   *   *   *   *   *   *   *   *
     **********************************************************************************************/

    @Override
    public void onItemSelected(Object item) {
        NavDirections action = null;

        switch ((String) item) {
            case "Personal Info":
                action = AccountFragmentDirections
                        .actionAccountFragmentToPersonalInfoFragment();
                break;
            case "Addresses":
                action = AccountFragmentDirections
                        .actionAccountFragmentToAddressesFragment();
                break;
            case "Payment":
                action = AccountFragmentDirections
                        .actionAccountFragmentToPaymentFragment();
                break;
            case "Use conditions":
                action = AccountFragmentDirections
                        .actionAccountFragmentToUseConditionsFragment();
                break;
        }

        assert action != null;
        Navigation.findNavController(this.requireView()).navigate(action);
    }
}
