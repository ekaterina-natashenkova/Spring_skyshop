package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productsBasket;
    private final StorageService storageService;


    public BasketService(ProductBasket productBasket, ProductBasket productsBasket, StorageService storageService) {
        this.productsBasket = productsBasket;
        this.storageService = storageService;
    }

    public void addProductBasket(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);
        if (!productOptional.isPresent()) {
            throw new IllegalArgumentException("Отсутствует продукт с id: " + id);
        }
        productsBasket.addProductsBasket();
    }

    public UserBasket getUserBasket(Product availableProducts) {
        Map<UUID, Integer> productMap = productsBasket.getProductsBasket();
        List<BasketItem> basketItems = productMap.entrySet().stream()
                .map(entry -> {
                    Optional<Product> product = storageService.getProductById(entry.getKey());
                    return new BasketItem(availableProducts, entry.getValue());
                })
                .collect(Collectors.toList());
        return new UserBasket(basketItems);

    }
}
