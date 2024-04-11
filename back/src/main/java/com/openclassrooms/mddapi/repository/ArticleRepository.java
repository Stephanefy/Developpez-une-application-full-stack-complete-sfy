package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.domain.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a JOIN a.themes t JOIN t.subscribers s WHERE s.id = :userId")
    List<Article> findAllBySubscribedThemes(@Param("userId") Long userId);

}
