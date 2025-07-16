package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    /**
     * Рассматриваем 3 сценария работы StorageService:
     * (1) Поиск в случае отсутствия объектов в StorageService.
     * (2) Поиск в случае, если объекты в StorageService есть, но нет подходящего.
     * (3) Поиск, когда есть подходящий объект в StorageService.
     */

    @Test
    void findPattern_WhenFindObject_ThenObjectIsEmpty() {

        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());

        // test
        Collection<SearchResult> results = searchService.findPattern("test");

        // check
        assertTrue(results.isEmpty());
        verify(storageService, times(1)).getAllSearchable();
    }

    @Test
    void findPattern_WhenObjectsInStorageService_ThenNoMatches() throws IllegalAccessException {

        Searchable searchable1 = new Article("book", "about nothing", UUID.randomUUID());
        Searchable searchable2 = new SimpleProduct("book", 10, UUID.randomUUID());

        when(storageService.getAllSearchable()).thenReturn(Stream.of(searchable1, searchable2).collect(Collectors.toList()));

        // test
        Collection<SearchResult> results = searchService.findPattern("coffee");

        // check
        assertTrue(results.isEmpty());
        verify(storageService, times(1)).getAllSearchable();
    }

    @Test
    void findPattern_WhenObjectsInStorageService_ThenWithMatches() throws IllegalAccessException {

        Searchable searchable3 = new Article("phone", "about phone", UUID.randomUUID());
        Searchable searchable4 = new DiscountedProduct("phone", 50, 10, UUID.randomUUID());
        Searchable searchable5 = new FixPriceProduct("milk", UUID.randomUUID());

        when(storageService.getAllSearchable()).thenReturn(Stream.of(searchable3, searchable4, searchable5).collect(Collectors.toList()));

        // test
        Collection<SearchResult> results = searchService.findPattern("phone");

        // check
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(searchResult -> searchResult.getName().equals("phone")));
        verify(storageService, times(1)).getAllSearchable();
    }

}