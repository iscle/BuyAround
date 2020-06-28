package cat.buyaround.app.model;

import java.io.Serializable;

public class ItemGroup implements Serializable {

    private int quantity;
    private Item item;
    private float totalPrice;

    public ItemGroup(int quantity, Item item, float totalPrice) {
        this.quantity = quantity;
        this.item = item;
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void addItem() {
        this.quantity++;
        this.totalPrice = this.totalPrice + item.getPrice();
    }

    public void substractItem() {
        if (quantity > 1) {
            this.quantity--;
            this.totalPrice = this.totalPrice - item.getPrice();
        }
    }
}
