package cat.buyaround.app.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import javax.inject.Inject;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.ImageViewPagerAdapter;
import cat.buyaround.app.databinding.FragmentStoreBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.utils.ZoomOutPageTransformer;
import cat.buyaround.app.viewmodel.StoreViewModel;
import dagger.android.support.DaggerFragment;

public class StoreFragment extends DaggerFragment {

    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentStoreBinding binding;
    private StoreViewModel storeViewModel;
    private MapView map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStoreBinding.inflate(inflater, container, false);

        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        storeViewModel = new ViewModelProvider(this, viewModelFactory).get(StoreViewModel.class);

        if (getArguments() != null) {
            storeViewModel.setStore(StoreFragmentArgs.fromBundle(getArguments()).getStore());
        }

        initViews();
    }

    private void initViews() {
        addStoreInfo();

        binding.storeShareBtn.setOnClickListener(v -> {
            checkForPermissions();
        });

        binding.favouriteCheckbox.setOnClickListener(v -> {
            // TODO: API CALL TO TOGGLE LIKE
        });
    }

    private void addStoreInfo() {
        configureImagesViewPager();
        configureBottomViewPager();

        binding.storeName.setText(storeViewModel.getStoreName());

        binding.storeDescription.setText(storeViewModel.getStoreDescription());

        binding.storeRating.setText(String.valueOf(storeViewModel.getStoreRating()));

        requestLocationPermissions();
    }

    private void configureBottomViewPager() {
        binding.productPacksViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.productPacksViewPager.setAdapter(new ScreenSlidePagerAdapter(this));
        binding.productPacksViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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

        new TabLayoutMediator(binding.tabLayout, binding.productPacksViewPager, (tab, position) -> {
            if (position == 0)
                tab.setText(R.string.store_fragment_products);
            else
                tab.setText(R.string.store_fragment_packs);

        }).attach();
    }

    private void requestLocationPermissions() {
        int permissionFineLocation = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCoarseLocation = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionWrite = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionInternet = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.INTERNET);
        int permissionNetworkState = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_NETWORK_STATE);

        if (permissionFineLocation != PackageManager.PERMISSION_GRANTED ||
                permissionCoarseLocation != PackageManager.PERMISSION_GRANTED ||
                permissionWrite != PackageManager.PERMISSION_GRANTED ||
                permissionInternet != PackageManager.PERMISSION_GRANTED ||
                permissionNetworkState != PackageManager.PERMISSION_GRANTED)
            askForLocationPermission();
        else
            initMap();

    }

    private void initMap() {
        // TODO: PUT A PIN IN THE MAP
        map = binding.mapView;
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(19.0);

        GeoPoint storeLocation = new GeoPoint(
                storeViewModel.getStoreDirection().getLatitude(),
                storeViewModel.getStoreDirection().getLongitude());

        mapController.setCenter(storeLocation);


        ArrayList<OverlayItem> items = new ArrayList<>();
        items.add(new OverlayItem(storeViewModel.getStoreName(), storeViewModel.getStoreDirection().getAddress(), storeLocation));

        ItemizedOverlayWithFocus<OverlayItem> overlay = new ItemizedOverlayWithFocus<>(requireContext(), items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        overlay.setFocusItemsOnTap(true);
        map.getOverlayManager().add(overlay);

    }

    private void askForLocationPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE}, 1);
    }

    private void configureImagesViewPager() {
        binding.storeViewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.storeViewpager.setAdapter(new ImageViewPagerAdapter(requireContext(), storeViewModel.getStoreImages()));
        binding.storeViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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
        binding.storeViewpager.setPageTransformer(new ZoomOutPageTransformer());
        new TabLayoutMediator(binding.storeTabLayout, binding.storeViewpager, (tab, position) -> {

        }).attach();
    }

    private void checkForPermissions() {
        int permissionWrite = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionWrite != PackageManager.PERMISSION_GRANTED || permissionRead != PackageManager.PERMISSION_GRANTED)
            askForPermission();
        else
            shareStore();
    }

    private void askForPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    private void shareStore() {
        // TODO
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(Fragment f) {
            super(f);
        }

        @Override
        public Fragment createFragment(int position) {
            if (position == 0)
                return new ScreenProductsFragment();
            else
                return new ScreenPacksFragment();
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
