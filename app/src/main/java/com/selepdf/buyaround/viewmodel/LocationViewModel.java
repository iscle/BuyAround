package com.selepdf.buyaround.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selepdf.buyaround.callback.StoreCallback;
import com.selepdf.buyaround.model.Store;
import com.selepdf.buyaround.model.UserRadius;
import com.selepdf.buyaround.network.BuyAroundRepository;

import javax.inject.Inject;

public class LocationViewModel extends ViewModel {

    private BuyAroundRepository buyAroundRepository;
    private MutableLiveData<Store[]> mStores;

    @Inject
    public LocationViewModel(BuyAroundRepository buyAroundRepository) {
        this.buyAroundRepository = buyAroundRepository;
        this.mStores = new MutableLiveData<>();
    }

    private void requestNearbyStores(UserRadius userRadius) {
        buyAroundRepository.getNearbyStores(userRadius, new StoreCallback() {
            @Override
            public void onStoresReceived(Store[] stores) {
                mStores.postValue(stores);
            }

            @Override
            public void onFailure(FailureError error) {

            }
        });
    }

    public LiveData<Store[]> getNearByStores(UserRadius userRadius) {
        //requestNearbyStores(userRadius); // TODO: UNCOMMENT
        return mStores;
    }
}
