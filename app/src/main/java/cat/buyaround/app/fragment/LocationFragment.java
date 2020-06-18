package cat.buyaround.app.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.CompactStoreListAdapter;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.FragmentLocationBinding;
import cat.buyaround.app.factory.ViewModelFactory;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.model.UserRadius;
import cat.buyaround.app.viewmodel.LocationViewModel;
import dagger.android.support.DaggerFragment;

public class LocationFragment extends DaggerFragment implements IListAdapter {

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private final int DEFAULT_RADIUS = 500;
    private final double DEFAULT_ZOOM = 16;
    @Inject
    protected ViewModelFactory viewModelFactory;
    private FragmentLocationBinding binding;
    private LocationViewModel locationViewModel;

    private LocationManager locationManager;
    private MapView map = null;
    private MyLocationNewOverlay mLocationOverlay;
    private ItemizedOverlayWithFocus<OverlayItem> mOverlay;
    private Polygon p;

    private RecyclerView storesRecyclerView;
    private CompactStoreListAdapter storeListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);

        binding = FragmentLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locationViewModel = new ViewModelProvider(this, viewModelFactory).get(LocationViewModel.class);

        initMap();

        initRV();

        requestPermissionsIfNecessary(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET
        });

        binding.radiusMetersNumEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // UNSUSED
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    binding.radiusMetersNumEt.setText(s);
                    System.out.println(s);
                    System.out.println(binding.radiusMetersNumEt.getText().toString());
                    subscribeObserver();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // UNUSED
            }
        });

        subscribeObserver();
    }

    private void initRV() {
        storesRecyclerView = binding.nearbyStoresRv;
        storeListAdapter = new CompactStoreListAdapter(getContext(), this);
        storesRecyclerView.setAdapter(storeListAdapter);
        storesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    private void adaptRadius(double latitude, double longitude, int radius) {
        if (p != null) {
            map.getOverlays().remove(p);
        }

        List<GeoPoint> circleRadius = Polygon.pointsAsCircle(
                new GeoPoint(latitude, longitude),
                radius);

        p = new Polygon(map);
        p.getFillPaint().setColor(getResources().getColor(R.color.colorCircleFill));
        p.getOutlinePaint().setColor(getResources().getColor(R.color.colorCircleBorder));
        p.setPoints(circleRadius);
        map.getOverlayManager().add(p);
    }

    private void subscribeObserver() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionsIfNecessary(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
            return;
        }
        Location currentLocation = locationManager.getLastKnownLocation(Objects.requireNonNull(locationManager.getBestProvider(new Criteria(), true)));

        if (currentLocation == null)
            return;

        double latitude = currentLocation.getLatitude();
        double longitude = currentLocation.getLongitude();
        int radius = binding.radiusMetersNumEt.getText().toString().trim().isEmpty() ?
                DEFAULT_RADIUS :
                Integer.parseInt(binding.radiusMetersNumEt.getText().toString().trim());

        adaptRadius(latitude, longitude, radius);

        UserRadius userRadius = new UserRadius(latitude, longitude, radius);

        locationViewModel.getNearByStores(userRadius).observe(getViewLifecycleOwner(), stores -> {
            if (stores != null && stores.length > 0) {
                binding.nearbyStoresNumTv.setText(String.valueOf(stores.length));
                storesRecyclerView.setVisibility(View.VISIBLE);
                binding.storesEmptyView.setVisibility(View.GONE);
            }
            storeListAdapter.setStores(stores);

            setStoreOverlays(stores);

        });
    }

    private void setStoreOverlays(Store[] stores) {
        if (mOverlay != null) {
            map.getOverlays().remove(mOverlay);
        }

        ArrayList<OverlayItem> items = new ArrayList<>();

        for (Store store : stores) {
            items.add(new OverlayItem(store.getName(),
                    store.getDescription(),
                    new GeoPoint(store.getDirection().getLatitude(),
                            store.getDirection().getLongitude())));
        }

        mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(requireContext(), items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                // TODO: OPEN SPECIFIC STORE FRAGMENT
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        mOverlay.setFocusItemsOnTap(true);
        map.getOverlayManager().add(mOverlay);
    }

    private void initMap() {
        map = binding.mapView;
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(DEFAULT_ZOOM);

        mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(requireContext()), map);
        mLocationOverlay.enableMyLocation();
        mLocationOverlay.enableFollowLocation();
        map.getOverlays().add(this.mLocationOverlay);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLocationOverlay.enableMyLocation();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationOverlay.disableMyLocation();
        map.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        ArrayList<String> permissionsToRequest = new ArrayList<>(Arrays.asList(permissions).subList(0, grantResults.length));
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onItemSelected(Object item) {
        if (item instanceof Store) {
            // TODO: OPEN SPECIFIC STORE FRAGMENT
        }
    }
}
