package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Pack;

public class PackResponse extends Response {
    private Pack[] packs;

    public Pack[] getPacks() {
        return packs;
    }
}
