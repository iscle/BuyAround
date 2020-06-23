package cat.buyaround.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import cat.buyaround.app.databinding.FragmentPaymentMethodsBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.viewmodel.PaymentMethodsViewModel;
import dagger.android.support.DaggerFragment;

public class PaymentMethodsFragment extends DaggerFragment {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentPaymentMethodsBinding binding;
    private PaymentMethodsViewModel paymentMethodsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentMethodsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        paymentMethodsViewModel = new ViewModelProvider(this, viewModelFactory).get(PaymentMethodsViewModel.class);

        initViews();
    }

    private void initViews() {
        binding.paymentCardsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        //binding.paymentCardsRv.setAdapter(new PaymentCardListAdapter());
    }
}
