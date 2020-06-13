package cat.buyaround.app.fragment;

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

import cat.buyaround.app.adapter.OrderListAdapter;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.FragmentOrdersBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.OrdersViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class OrdersFragment extends DaggerFragment implements IListAdapter {
    private FragmentOrdersBinding binding;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private OrdersViewModel ordersViewModel;

    private RecyclerView lastOrdersrecyclerView;
    private OrderListAdapter lastOrderListAdapter;
    private RecyclerView repeatedOrdersrecyclerView;
    private OrderListAdapter repeatedOrderListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ordersViewModel = new ViewModelProvider(this, viewModelFactory).get(OrdersViewModel.class);

        lastOrderListAdapter = new OrderListAdapter(getContext(), this);
        repeatedOrderListAdapter = new OrderListAdapter(getContext(), this);

        lastOrdersrecyclerView = binding.ordersLastRecyclerView;
        lastOrdersrecyclerView.setAdapter(lastOrderListAdapter);
        lastOrdersrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(lastOrdersrecyclerView.getContext(), RecyclerView.VERTICAL);
        lastOrdersrecyclerView.addItemDecoration(dividerItemDecoration);

        repeatedOrdersrecyclerView = binding.ordersRepeatedRecyclerView;
        repeatedOrdersrecyclerView.setAdapter(repeatedOrderListAdapter);
        repeatedOrdersrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(repeatedOrdersrecyclerView.getContext(), RecyclerView.VERTICAL);
        repeatedOrdersrecyclerView.addItemDecoration(dividerItemDecoration2);

        subscribeObservers();
    }

    private void subscribeObservers() {
        ordersViewModel.getLastUserOrders().observe(getViewLifecycleOwner(), orders -> {
            if (orders != null && orders.length > 0) {
                lastOrdersrecyclerView.setVisibility(View.VISIBLE);
                repeatedOrdersrecyclerView.setVisibility(View.VISIBLE);
                binding.textView2.setVisibility(View.VISIBLE);
                binding.textView3.setVisibility(View.VISIBLE);
                binding.emptyView.setVisibility(View.GONE);
            }
            lastOrderListAdapter.setOrders(orders);
        });

        ordersViewModel.getRepeatedUserOrders().observe(getViewLifecycleOwner(), orders -> {
            repeatedOrderListAdapter.setOrders(orders);
        });
    }

    @Override
    public void onItemSelected(Object item) {
        // TODO: OPEN ORDER FRAGMENT WITH THE DETAILS
    }
}
