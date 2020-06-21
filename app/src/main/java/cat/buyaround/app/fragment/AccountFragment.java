package cat.buyaround.app.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.ContentListAdapter;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.auth.UserManager;
import cat.buyaround.app.databinding.FragmentAccountBinding;
import cat.buyaround.app.databinding.ItemContentBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.User;
import dagger.android.support.DaggerFragment;

public class AccountFragment extends DaggerFragment {
    private static final String TAG = "AccountFragment";

    @Inject
    protected ViewModelFactory viewModelFactory;
    @Inject
    UserManager userManager;
    private FragmentAccountBinding binding;

    private boolean registered;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(UserManager.ACTION_USER_UPDATED)) {
                Log.d(TAG, "onReceive: Updating user from broadcast");
                updateUser();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.loginBtn.setOnClickListener(v -> {
            NavDirections action = AccountFragmentDirections
                    .actionAccountFragmentToLoginFragment();

            Navigation.findNavController(v).navigate(action);
        });

        setupItemContent(binding.personalInfo, "Personal info",
                getListener(AccountFragmentDirections
                .actionAccountFragmentToPersonalInfoFragment()));

        setupItemContent(binding.orders, "Previous orders",
                getListener(AccountFragmentDirections
                .actionAccountFragmentToOrdersFragment()));

        setupItemContent(binding.addresses, "My addresses",
                getListener(AccountFragmentDirections
                .actionAccountFragmentToAddressesFragment()));

        setupItemContent(binding.paymentMethods, "My payment methods",
                getListener(AccountFragmentDirections
                .actionAccountFragmentToPaymentFragment()));

        setupItemContent(binding.legal, "Legal",
                getListener(AccountFragmentDirections
                .actionAccountFragmentToLegalFragment()));

        setupItemContent(binding.aboutUs, "About us",
                getListener(AccountFragmentDirections
                .actionAccountFragmentToAboutUsFragment()));

        setupItemContent(binding.logOut, "Log out", v -> {
            userManager.logout();
        });

        registered = false;
    }

    private void setupItemContent(ItemContentBinding b, String title, View.OnClickListener listener) {
        b.itemContentTitle.setText(title);
        b.getRoot().setOnClickListener(listener);
    }

    private View.OnClickListener getListener(NavDirections action) {
        return v -> Navigation.findNavController(requireView()).navigate(action);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!registered) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(UserManager.ACTION_USER_UPDATED);
            LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, filter);
            registered = true;
            updateUser();
        }
    }

    @Override
    public void onPause() {
        if (registered) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);
            registered = false;
        }

        super.onPause();
    }

    private void updateUser() {
        if (userManager.isLoggedIn()) {
            User user = userManager.getUser();
            binding.profileUsername.setText(user.getName());
            binding.profileEmail.setText(user.getEmail());

            Glide.with(this)
                    .asBitmap()
                    .placeholder(R.drawable.ic_thumbnail)
                    .load(user.getProfilePicture())
                    .into(binding.profileThumbnail);

            setLoggedIn(true);
        } else {
            setLoggedIn(false);
        }
    }

    private void setLoggedIn(boolean loggedIn) {
        if (loggedIn) {
            binding.notLoggedInLayout.setVisibility(View.GONE);
            binding.loggedInLayout.setVisibility(View.VISIBLE);

            binding.personalInfo.getRoot().setVisibility(View.VISIBLE);
            binding.orders.getRoot().setVisibility(View.VISIBLE);
            binding.addresses.getRoot().setVisibility(View.VISIBLE);
            binding.paymentMethods.getRoot().setVisibility(View.VISIBLE);
            binding.logOut.getRoot().setVisibility(View.VISIBLE);
        } else {
            binding.notLoggedInLayout.setVisibility(View.VISIBLE);
            binding.loggedInLayout.setVisibility(View.GONE);

            binding.personalInfo.getRoot().setVisibility(View.GONE);
            binding.orders.getRoot().setVisibility(View.GONE);
            binding.addresses.getRoot().setVisibility(View.GONE);
            binding.paymentMethods.getRoot().setVisibility(View.GONE);
            binding.logOut.getRoot().setVisibility(View.GONE);
        }
    }
}
