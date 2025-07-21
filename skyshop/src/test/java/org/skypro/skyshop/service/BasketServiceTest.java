package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private StorageService storageService;
    
    @Mock
    private ProductBasket productBasket;

    @InjectMocks
    private BasketService basketService;

    /**
     * Рассматриваем 4 сценария работы ProductBasket:
     * (1) Добавление несуществующего товара в корзину приводит к выбросу исключения.
     * (2) Добавление существующего товара вызывает метод addProduct у мока ProductBasket.
     * (3) Метод getUserBasket возвращает пустую корзину, если ProductBasket пуст.
     * (4) Метод getUserBasket возвращает подходящую корзину, если в ProductBasket есть товары.
     */

    @Test
    void addProductBasket_WhenNoSuchProduct_ThenThrowsException() throws IllegalAccessException {

        Searchable addingProduct = new SimpleProduct("apple", 120, UUID.randomUUID());
        when(storageService.getProductById(addingProduct.getId())).thenReturn(Optional.empty());

        productBasket.addProductsBasket(UUID.randomUUID());
        assertThrows(NoSuchProductException.class, () -> basketService.addProductBasket(addingProduct.getId()));

        verify(productBasket, never()).addProductsBasket(addingProduct.getId());
    }

    @Test
    void addProductBasket_WhenProductExist_ThenProductAdding() throws IllegalAccessException {

        Product addingProduct2 = new SimpleProduct("чай", 120, UUID.randomUUID());
        when(storageService.getProductById(addingProduct2.getId())).thenReturn(Optional.of(addingProduct2));

        basketService.addProductBasket(addingProduct2.getId());

        verify(productBasket, only()).addProductsBasket(addingProduct2.getId());
    }

    @Test
    void getUserBasket_WhenProductBasketIsEmpty_ThenReturnIsEmptyBasket() {

        when(productBasket.getProductsBasket()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();

        assertNotNull(userBasket);
        assertTrue(userBasket.getBasketItems().isEmpty());
        assertEquals(0, userBasket.getTotal());

        verify(productBasket, only()).getProductsBasket();
        verifyNoInteractions(storageService);
    }

    @Test
    void getUserBasket_WhenProductBasketExists_ThenReturnExistsBasket() throws IllegalAccessException {

        Product addingProduct3 = new SimpleProduct("кофе", 120, UUID.randomUUID());
        UserBasket userBasket = new UserBasket(List.of(new BasketItem(addingProduct3,1)));
        when(productBasket.getProductsBasket()).thenReturn(Map.of(addingProduct3.getId(), 1));
        when(storageService.getProductById(addingProduct3.getId())).thenReturn(Optional.of(addingProduct3));

        assertEquals(userBasket.getBasketItems(),userBasket);

        verify(productBasket, only()).getProductsBasket();
        verify(storageService, only()).getProductById(addingProduct3.getId());
    }

}