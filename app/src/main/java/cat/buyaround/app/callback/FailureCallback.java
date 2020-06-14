package cat.buyaround.app.callback;

import cat.buyaround.app.network.model.SimpleResponse;

public interface FailureCallback {
    void onFailure(SimpleResponse.Status error);
}
