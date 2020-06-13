package cat.buyaround.app.network.model;

import cat.buyaround.app.model.Product;

public class ProductResponse extends Response {
    private Product[] products;

    public Product[] getProducts() {
        return products;
    }
}
