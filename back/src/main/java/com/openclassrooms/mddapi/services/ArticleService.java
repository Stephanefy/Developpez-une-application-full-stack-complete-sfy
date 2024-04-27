package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.domains.models.Article;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleService {


    List<Article> getAllArticles();

    List<Article> getAllRelatedThemeArticles(Long userId);

    Article createArticle(Article article, Long authorId, Set<Long> themesId);

    Optional<Article> getArticleById(String id);


    Article updateArticle(String id, Article updatedArticle);


    boolean deleteArticle(String id);



}
