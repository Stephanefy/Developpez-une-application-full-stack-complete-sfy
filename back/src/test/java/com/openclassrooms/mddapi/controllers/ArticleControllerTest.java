package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.domains.dtos.article.ArticleDTO;
import com.openclassrooms.mddapi.domains.dtos.article.CreateArticleDTO;
import com.openclassrooms.mddapi.domains.models.Article;
import com.openclassrooms.mddapi.services.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@Log4j2
@ExtendWith(MockitoExtension.class)
class ArticleControllerTest {

    @Mock
    private ArticleService mockArticleService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ArticleController articleController;


    @Test
    void whenArticleIsUpdated_AndSucceed_ThenReturnSuccess() {
        String articleId = "1";
        CreateArticleDTO articleDto = new CreateArticleDTO();
        Article article = new Article();
        Article updatedArticle = new Article(); // Create an updated article object

        when(mockArticleService.updateArticle(articleId, article)).thenReturn(updatedArticle);
        when(modelMapper.map(articleDto, Article.class)).thenReturn(article);
        when(modelMapper.map(updatedArticle, ArticleDTO.class)).thenReturn(new ArticleDTO());

        ResponseEntity<?> response = articleController.updateArticle(articleId, articleDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void whenAllArticlesAreFetched_AndSucceed_ThenReturnCorrectListOfArticles() {
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        Article article2 = new Article();
        articles.add(article);
        articles.add(article2);

        when(mockArticleService.getAllArticles()).thenReturn(articles);
        when(modelMapper.map(any(Article.class), eq(ArticleDTO.class))).thenReturn(new ArticleDTO());

        ResponseEntity<List<ArticleDTO>> response = articleController.getAllArticles();
        List<?> responseBody = response.getBody();

        log.info("{}", responseBody);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size()); // Assert the size of the list
        verify(mockArticleService, times(1)).getAllArticles();
    }

}