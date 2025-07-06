package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final double FIX_PRICE_PRODUCT = 50;
    private final UUID id;

    public FixPriceProduct(String titleProduct, UUID id) throws IllegalAccessException {
        super(titleProduct);
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public double getPriceProduct() {
        return FIX_PRICE_PRODUCT;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getTitleProduct() + ": Фиксированная цена " + FIX_PRICE_PRODUCT;
    }


}
