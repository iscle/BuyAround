package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Product;

public class ProductResponse extends SimpleResponse {
    private Product[] products;

    public Product[] getProducts() {
        return products;
    }
}
