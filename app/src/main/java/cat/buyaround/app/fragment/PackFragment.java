package cat.buyaround.app.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayoutMediator;

import javax.inject.Inject;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.ImageViewPagerAdapter;
import cat.buyaround.app.adapter.PackProductListAdapter;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.FragmentPackBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.Product;
import cat.buyaround.app.utils.ZoomOutPageTransformer;
import cat.buyaround.app.viewmodel.PackViewModel;
import dagger.android.support.DaggerFragment;

public class PackFragment extends DaggerFragment implements IListAdapter {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentPackBinding binding;
    private PackViewModel packViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPackBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        packViewModel = new ViewModelProvider(this, viewModelFactory).get(PackViewModel.class);

        if (getArguments() != null) {
            packViewModel.setPack(PackFragmentArgs.fromBundle(getArguments()).getPack());
        }

        initViews();
    }

    private void initViews() {
        addPackInfo();

        addStoreInfo();

        binding.packAddBtn.setOnClickListener(v -> {
            int productQuantity = Integer.parseInt(binding.packQuantity.getText().toString());
            binding.packQuantity.setText(String.valueOf(productQuantity + 1));
        });

        binding.packSubstractBtn.setOnClickListener(v -> {
            int productQuantity = Integer.parseInt(binding.packQuantity.getText().toString());
            if (productQuantity > 1) {
                binding.packQuantity.setText(String.valueOf(productQuantity - 1));
            }
        });

        binding.packAddCartBtn.setOnClickListener(v -> {
            // TODO: ADD TO CART API CALL
        });

        binding.packShareBtn.setOnClickListener(v -> {
            checkForPermissions();
        });

        binding.favouriteCheckbox.setOnClickListener(v -> {
            // TODO: API CALL TO TOGGLE LIKE
        });
    }

    private void addStoreInfo() {
        binding.storeName.setText(packViewModel.getPackStore().getName());

        binding.storeAddress.setText(packViewModel.getPackStore().getDirection().getAddress());
        Glide.with(requireContext())
                .asBitmap()
                .placeholder(R.drawable.ic_thumbnail)
                .load(packViewModel.getPackStore().getImages()[0])
                .into(binding.storeIv);

        binding.storeCardview.setOnClickListener(v -> {
            PackFragmentDirections.ActionPackFragmentToStoreFragment action =
                    PackFragmentDirections.actionPackFragmentToStoreFragment();
            action.setStore(packViewModel.getPackStore());

            Navigation.findNavController(v).navigate(action);
        });
    }

    private void addPackInfo() {
        configureViewPager();

        binding.packName.setText(packViewModel.getPackName());

        binding.packDescription.setText(packViewModel.getPackDescription());

        binding.packPrice.setText(String.valueOf(packViewModel.getPackPrice()));

        binding.packPoints.setText(String.valueOf(packViewModel.getPackPoints()));

        binding.packRating.setText(String.valueOf(packViewModel.getPackRating()));

        binding.packProductsRv.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        binding.packProductsRv.setAdapter(new PackProductListAdapter(packViewModel.getPackProducts(), PackFragment.this));
    }

    private void configureViewPager() {
        binding.packViewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.packViewpager.setAdapter(new ImageViewPagerAdapter(requireContext(), packViewModel.getPackImages()));
        binding.packViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        binding.packViewpager.setPageTransformer(new ZoomOutPageTransformer());
        new TabLayoutMediator(binding.packTabLayout, binding.packViewpager, (tab, position) -> {

        }).attach();
    }

    private void checkForPermissions() {
        int permissionWrite = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionWrite != PackageManager.PERMISSION_GRANTED || permissionRead != PackageManager.PERMISSION_GRANTED)
            askForPermission();
        else
            sharePack();
    }

    private void askForPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    private void sharePack() {
        // TODO
    }

    @Override
    public void onItemSelected(Object item) {
        PackFragmentDirections.ActionPackFragmentToProductFragment action =
                PackFragmentDirections.actionPackFragmentToProductFragment();
        action.setProduct((Product) item); //TODO: FIX... IS IT A PRODUCT OR IS IT AN ORDERPRODUCT??

        Navigation.findNavController(binding.getRoot()).navigate(action);
    }
}
