package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;
    //private final Map<UUID, Product> availableProducts;

    public StorageService() throws IllegalAccessException {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
       // this.availableProducts = availableProducts;
        createTestObjects();
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    public Collection<Product> products() {
        return products.values();
    }

    public Collection<Article> articles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchable() {
        return Stream.concat(products.values().stream(), articles.values().stream())
                .collect(Collectors.toList());
    }

    private void createTestObjects() throws IllegalAccessException {
        Product p1 = new SimpleProduct("чай", 200, UUID.randomUUID());
        this.products.put(p1.getId(), p1);
        Product p2 = new SimpleProduct("кофе", 450, UUID.randomUUID());
        this.products.put(p2.getId(), p2);
        Product p3 = new DiscountedProduct("вода", 100, 10, UUID.randomUUID());
        this.products.put(p3.getId(), p3);
        Product p4 = new DiscountedProduct("сок", 150, 20, UUID.randomUUID());
        this.products.put(p4.getId(), p4);
        Product p5 = new FixPriceProduct("молоко", UUID.randomUUID());
        this.products.put(p5.getId(), p5);
        Product p6 = new FixPriceProduct("кефир", UUID.randomUUID());
        this.products.put(p6.getId(), p6);
        Product p7 = new DiscountedProduct("чай черный", 200, 30, UUID.randomUUID());
        this.products.put(p7.getId(), p7);
        Product p8 = new DiscountedProduct("чай зеленый", 200, 35, UUID.randomUUID());
        this.products.put(p8.getId(), p8);
        Product p9 = new DiscountedProduct("чай фруктовый", 200, 20, UUID.randomUUID());
        this.products.put(p9.getId(), p9);
        Product p10 = new FixPriceProduct("сок яблочный", UUID.randomUUID());
        this.products.put(p10.getId(), p10);
        Product p11 = new FixPriceProduct("сок персиковый", UUID.randomUUID());
        this.products.put(p11.getId(), p11);
        Product p12 = new FixPriceProduct("сок томатный", UUID.randomUUID());
        this.products.put(p12.getId(), p12);
        Product p13 = new SimpleProduct("морс", 100, UUID.randomUUID());
        this.products.put(p13.getId(), p13);
        Product p14 = new DiscountedProduct("морс клюквенный", 200, 50, UUID.randomUUID());
        this.products.put(p14.getId(), p14);
        Product p15 = new SimpleProduct("м", 100, UUID.randomUUID()); // ошибка названия - ничего не введено
        this.products.put(p15.getId(), p15);
        Product p16 = new DiscountedProduct("морс брусничный", 10, 35, UUID.randomUUID()); // ошибка цены - 0
        this.products.put(p16.getId(), p16);
        Product p17 = new DiscountedProduct("морс смородиновый", 200, 10, UUID.randomUUID()); // ошибка скидки - 101
        this.products.put(p17.getId(), p17);

        Article a1 = new Article("виды чая", "о пользе и вреде различных видов чая, при определенной температуре заваривания", UUID.randomUUID());
        this.articles.put(a1.getId(), a1);
        Article a2 = new Article("кофе для здоровья", "о пользе и вреде кофе, при высоком и низком давлении", UUID.randomUUID());
        this.articles.put(a2.getId(), a2);
        Article a3 = new Article("все о воде", "о необходимости потребления достаточного количества воды в течении дня для людей разного возраста", UUID.randomUUID());
        this.articles.put(a3.getId(), a3);
        Article a4 = new Article("про сок", "о пользе и вреде свежевыжатого сока и сокосодержащих напитков", UUID.randomUUID());
        this.articles.put(a4.getId(), a4);
        Article a5 = new Article("о молоке", "о качестве современного пакетированного молока, представленного в магазинах", UUID.randomUUID());
        this.articles.put(a5.getId(), a5);
        Article a6 = new Article("кефир - мифы и реальность", "о кефире, как о разновидности кисломолочной продукции", UUID.randomUUID());
        this.articles.put(a6.getId(), a6);

    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }


}
