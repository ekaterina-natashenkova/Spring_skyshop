package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class BasketService {

    private final ProductBasket productsBasket;
    private final StorageService storageService;


    public BasketService(ProductBasket productBasket, ProductBasket productsBasket, StorageService storageService) {
        this.productsBasket = productsBasket;
        this.storageService = storageService;
    }

    public void addProductBasket(UUID id) {
        if (!storageService.getProductById(id).isPresent()) {
            throw new IllegalArgumentException("Отсутствует продукт с id: " + id);
        }
        productsBasket.addProductsBasket(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItemsList = productsBasket.getProductsBasket().entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()).orElseThrow(), entry.getValue()))
                .toList();
        return new UserBasket(basketItemsList);
    }


}
