package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {

    private String titleProduct;

    public Product(String titleProduct) throws IllegalAccessException {
        if (titleProduct == null || titleProduct.isBlank()) {
            throw new IllegalAccessException("Введено некорректное название продукта");  //  - проверка на null и пустую строку
        }
        this.titleProduct = titleProduct;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }

    public abstract double getPriceProduct();

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getTitleProduct();
    }
    @JsonIgnore
    @Override
    public String getTypeContent() {
        return "PRODUCT";
    }


    @Override
    public String toString() {
        return titleProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(titleProduct, product.titleProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titleProduct);
    }


}