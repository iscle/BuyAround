package cat.buyaround.app.callback;

public interface LoginCallback {
    void onSuccess();
    void onFailure(LoginError loginError);

    enum LoginError {
        WRONG_PASSWORD,
        INTERNAL_ERROR,
        NETWORK_ERROR,
        MISSING_PARAMETERS
    }
}
