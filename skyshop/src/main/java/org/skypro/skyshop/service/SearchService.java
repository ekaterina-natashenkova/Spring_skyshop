package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {

    private final Map<String, Product> products;
    private final Map<String, Article> articles;
    private final StorageService storageService;


    public SearchService(Map<String, Product> products, Map<String, Article> articles, StorageService storageService) {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        this.storageService = storageService;
    }

    @GetMapping("/search")
    public Collection<Searchable> getAllSearchable() {
        return Stream.concat(products.values().stream(), articles.values().stream())
                .collect(Collectors.toList());
    }

    @GetMapping("/search/pattern")
    public List<SearchResult> findPattern (@RequestParam String pattern) {
        return this.storageService.getAllSearchable().stream()
                .filter(searchable -> searchable.getSearchTerm().toLowerCase().contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }

}