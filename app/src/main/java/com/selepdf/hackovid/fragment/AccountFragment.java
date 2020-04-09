package com.selepdf.hackovid.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.selepdf.hackovid.R;
import com.selepdf.hackovid.adapter.ContentListAdapter;
import com.selepdf.hackovid.adapter.callback.IListAdapter;
import com.selepdf.hackovid.databinding.FragmentAccountBinding;
import com.selepdf.hackovid.factory.ViewModelFactory;
import com.selepdf.hackovid.model.User;
import com.selepdf.hackovid.viewmodel.AccountViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class AccountFragment extends Fragment implements IListAdapter {

    private FragmentAccountBinding binding;
    private final List<String> mContents = new ArrayList<>(
            Arrays.asList("Personal Info", "Adresses", "Payment", "Notifications", "Use conditions"));

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

    }

    private void subscribeObservers() {
        viewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.profileUsername.setText(user.getName());
                binding.profileEmail.setText(user.getEmail());
                Glide.with(AccountFragment.this)
                        .asBitmap()
                        .placeholder(R.drawable.ic_thumbnail)
                        .load(user.getProfilePicture())
                        .into(binding.profileThumbnail);
            }
        });
    }


    /**********************************************************************************************
     *   *   *   *   *   *   *   *   AdapterCallback   *   *   *   *   *   *   *   *   *
     **********************************************************************************************/

    @Override
    public void onItemSelected(Object item) {
        System.out.println("Item: " +  (String)item);
    }
}
