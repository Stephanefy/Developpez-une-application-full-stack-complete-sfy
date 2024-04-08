package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.domain.models.Article;
import com.openclassrooms.mddapi.domain.models.Theme;
import com.openclassrooms.mddapi.domain.models.User;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Log4j2
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ThemeRepository themeRepository;


    @Override
    @Transactional
    public List<Article> getAllArticles() {

        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> {
            Hibernate.initialize(article.getAuthor());
        });

        log.info("returned articles {}", articles);

        return articles;
    }


    @Override
    public Article createArticle(Article article, Long authorId, Set<Long> themesId) {
        // Fetch the author by ID
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + authorId));
        article.setAuthor(author);

        // Fetch themes by IDs and set them
        Set<Theme> themes = new HashSet<>(themeRepository.findAllById(themesId));
//        if (themesId!= null && !themesId.isEmpty()) {
//            themes = (Set<Theme>) themeRepository.findAllById(themesId);
//            if (themes.size() != themesId.size()) {
//                throw new EntityNotFoundException("One or more themes not found.");
//            }
//        }
        log.info("themes {}", themes);
        article.setThemes(themes);
        return articleRepository.save(article);
    }

    @Override
    public Optional<Article> getArticleById(String id) {
        return articleRepository.findById(Long.valueOf(id));
    }

    @Override
    public Article updateArticle(String id, Article updatedArticle) {


        Long convertedId = Long.valueOf(id);
        Optional<Article> article = articleRepository.findById(convertedId);

        if (article == null) {
            throw new NotFoundException("Article with such id was not found");
        }

        updatedArticle.setId(convertedId);

        return articleRepository.save(updatedArticle);
    }

    @Override
    public boolean deleteArticle(String id) {

        Long convertedId = Long.valueOf(id);

        Optional<Article> article = articleRepository.findById(convertedId);

        if (article == null) {
            throw new NotFoundException("Article with such id was not found");
        }

        articleRepository.deleteById(convertedId);

        return true;

    }
}
