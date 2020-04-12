package com.selepdf.buyaround.fragment;

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

import com.selepdf.buyaround.adapter.NotificationListAdapter;
import com.selepdf.buyaround.adapter.callback.IListAdapter;
import com.selepdf.buyaround.databinding.FragmentNotificationsBinding;
import com.selepdf.buyaround.factory.ViewModelFactory;
import com.selepdf.buyaround.viewmodel.NotificationsViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class NotificationsFragment extends DaggerFragment implements IListAdapter {
    private FragmentNotificationsBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private NotificationsViewModel notificationsViewModel;

    private RecyclerView recyclerView;
    private NotificationListAdapter notificationListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notificationsViewModel = new ViewModelProvider(this, viewModelFactory).get(NotificationsViewModel.class);

        notificationsViewModel.getUserNotifications();

        recyclerView = binding.notificationsRecyclerView;
        notificationListAdapter = new NotificationListAdapter(getContext(), this);
        recyclerView.setAdapter(notificationListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        subscribeObserver();
    }

    private void subscribeObserver() {
        notificationsViewModel.getUserNotifications().observe(getViewLifecycleOwner(), notifications -> {
            if (notifications != null && notifications.length > 0) {
                recyclerView.setVisibility(View.VISIBLE);
                binding.emptyView.setVisibility(View.GONE);
            }
            notificationListAdapter.setNotifications(notifications);
        });
    }

    @Override
    public void onItemSelected(Object item) {
        // Do nothing
    }
}
