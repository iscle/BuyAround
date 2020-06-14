package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Pack;

public class PackResponse extends SimpleResponse {
    private Pack[] packs;

    public Pack[] getPacks() {
        return packs;
    }
}
