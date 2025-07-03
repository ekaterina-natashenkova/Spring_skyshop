package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {

    private double priceProduct;
    private final UUID id;

    public SimpleProduct(String titleProduct, double priceProduct, UUID id) throws IllegalAccessException {
        super(titleProduct);
        if (priceProduct <= 0.0) {
            throw new IllegalAccessException("Введена некорректная цена продукта");  //  - должно быть строго больше 0
        }
        this.priceProduct = priceProduct;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public double getPriceProduct() {
        return priceProduct;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getTitleProduct() + ": " + priceProduct;
    }


}
