package org.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {

    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(UUID id, String name, String contentType) {
        this.id = id.toString();
        this.name = name;
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public static SearchResult fromSearchable (Searchable searchable) {
        return new SearchResult(searchable.getId(), searchable.getSearchTerm(), searchable.getTypeContent());
    }
}
