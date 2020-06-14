package cat.buyaround.app.network.model;

public class SimpleResponse {

    private Status status;

    public Status getStatus() {
        return status;
    }

    public enum Status {
        OK,
        INTERNAL_ERROR,
        WRONG_PASSWORD,
        MISSING_PARAMETERS,
        WEAK_PASSWORD,
        EXISTING_EMAIL,
        INVALID_TOKEN,
        NETWORK_FAILURE,
    }
}
