package cat.buyaround.app.model;

import java.util.Date;
import java.util.List;

public class Order {
    private Date date;
    private List<ItemGroup> items;
    private float totalPrice;
    private Store store;

    public Order(Date date, List<ItemGroup> items, float totalPrice, Store store) {
        this.date = date;
        this.items = items;
        this.totalPrice = totalPrice;
        this.store = store;
    }
}
