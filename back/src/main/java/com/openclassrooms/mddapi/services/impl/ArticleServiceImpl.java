package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.domains.models.Article;
import com.openclassrooms.mddapi.domains.models.Theme;
import com.openclassrooms.mddapi.domains.models.User;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
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

    private ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ThemeRepository themeRepository;


    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository, ThemeRepository themeRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
    }


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
    public List<Article> getAllRelatedThemeArticles(Long userId) {
        return articleRepository.findAllBySubscribedThemes(userId);
    }


    @Override
    public Article createArticle(Article article, Long authorId, Set<Long> themesId) {
        // Fetch the author by ID
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + authorId));
        article.setAuthor(author);

        // Fetch themes by IDs and set them
        Set<Theme> themes = new HashSet<>(themeRepository.findAllById(themesId));

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
