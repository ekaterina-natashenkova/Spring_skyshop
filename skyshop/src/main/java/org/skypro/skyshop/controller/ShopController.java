package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class ShopController {

    @GetMapping("/products")
    public Collection<Product> getAllProducts(StorageService storageService) {
        return storageService.getProducts().values();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles(StorageService storageService) {
        return storageService.getArticles().values();
    }

    @GetMapping("/search")
    public ArrayList<Searchable> getAllSearchable(SearchService searchService) {
        return new ArrayList<>(searchService.getAllSearchable());
    }

}

