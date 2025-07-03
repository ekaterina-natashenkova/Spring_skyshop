package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    String PRODUCT = "PRODUCT";
    String ARTICLE = "ARTICLE";

    UUID id = null;

    default UUID getId() {
        return id;
    }

    String getSearchTerm();

    String getTypeContent();

    default String getStringRepresentation() {
        return getSearchTerm() + " - " + getTypeContent();
    }
}

