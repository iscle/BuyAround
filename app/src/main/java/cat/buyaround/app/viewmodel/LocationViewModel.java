package cat.buyaround.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.buyaround.app.callback.StoreCallback;
import cat.buyaround.app.model.Direction;
import cat.buyaround.app.model.Store;
import cat.buyaround.app.model.UserRadius;
import cat.buyaround.app.network.BuyAroundRepository;

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
        modifyDataTest(); // TODO: DELETE
        return mStores;
    }

    private void modifyDataTest() {
        Store[] stores = new Store[1];

        Store store = new Store("Test name", "Test description");
        store.setRating(4.9f);
        Direction direction = new Direction(37.423325, -122.078159);
        direction.setAddress("Test name store address");
        store.setDirection(direction);

        stores[0] = store;

        mStores.postValue(stores);
    }
}
