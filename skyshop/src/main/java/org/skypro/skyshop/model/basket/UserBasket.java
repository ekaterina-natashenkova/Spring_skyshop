package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {

    private final List<BasketItem> basketItems;
    private final double total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.total = calculateTotal();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public double getTotal() {
        return total;
    }

    private double calculateTotal() {
        return basketItems.stream()
                .mapToDouble(item -> item.getProduct().getPriceProduct() * item.getQuantity())
                .sum();
    }


}
