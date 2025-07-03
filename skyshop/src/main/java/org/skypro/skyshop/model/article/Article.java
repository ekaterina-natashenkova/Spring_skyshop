package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

import static org.skypro.skyshop.model.search.Searchable.ARTICLE;

public final class Article implements Searchable {

    private String titleArticle;
    private String textArticle;
    private final UUID id;

    public Article(String titleArticle, String textArticle, UUID id) {
        this.titleArticle = titleArticle;
        this.textArticle = textArticle;
        this.id = id;
    }

    public String getTitleArticle() {
        return titleArticle;
    }

    public String getTextArticle() {
        return textArticle;
    }

    public UUID getId() {
        return id;
    }
    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getTitleArticle();
    }
    @JsonIgnore
    @Override
    public String getTypeContent() {
        return ARTICLE;
    }


    @Override
    public String toString() {
        return titleArticle + " - " + textArticle;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Article article)) return false;
        return Objects.equals(titleArticle, article.titleArticle);
    }

}
