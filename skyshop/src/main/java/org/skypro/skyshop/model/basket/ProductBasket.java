package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {

    private final Map<UUID, Integer> productsBasket;

    public ProductBasket() {
        this.productsBasket = new HashMap<>();
    }

    public Map<UUID, Integer> getProductsBasket() {
        return Collections.unmodifiableMap(productsBasket);
    }

    public void addProductsBasket(UUID id) {
        if (!productsBasket.containsKey(id)) {
            productsBasket.put(id, 1);
        } else {
            productsBasket.put(id, productsBasket.get(id) + 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductBasket that)) return false;
        return Objects.equals(productsBasket, that.productsBasket);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productsBasket);
    }

    @Override
    public String toString() {
        return "ProductBasket{" + "productsBasket=" + productsBasket + '}';
    }


}
