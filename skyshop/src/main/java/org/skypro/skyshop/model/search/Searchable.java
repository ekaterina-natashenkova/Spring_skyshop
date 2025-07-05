package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    default UUID getId() {
        return UUID.randomUUID();
    }

    String getSearchTerm();

    String getTypeContent();

    default String getStringRepresentation() {
        return getSearchTerm() + " - " + getTypeContent();
    }
}

