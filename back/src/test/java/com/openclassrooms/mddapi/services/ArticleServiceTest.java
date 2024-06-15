package com.openclassrooms.mddapi.services;


import com.openclassrooms.mddapi.domains.models.Article;
import com.openclassrooms.mddapi.domains.models.Theme;
import com.openclassrooms.mddapi.domains.models.User;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.services.impl.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @Mock
    private ArticleRepository mockArticleRepository;

    @InjectMocks
    private ArticleServiceImpl articleServiceUnderTest;

    @Mock
    private ThemeRepository mockThemeRepository;

    @Mock
    private UserRepository mockUserRepository;

    @Mock

    private List<Article> articles;


    @BeforeEach
    void setup() {

        List<Article> articles = Arrays.asList(
                new Article(),
                new Article()
        );
    }


    @Test
    void whenRequestFindAllArticleService_ThenReturnArticleList() {
        List<Article> articles = Arrays.asList(
                new Article(),
                new Article()
        );
        when(mockArticleRepository.findAll()).thenReturn(articles);

        List<Article> returnedArticles = articleServiceUnderTest.getAllArticles();

        assertEquals(articles, returnedArticles);
    }

    @Test
    void whenRequestCreateANewArticleService_ThenReturnCreatedArticle() {
        Article newArticle = new Article();
        Long authorId = 1L;
        Set<Long> themeIds = new HashSet<>(Arrays.asList(1L, 2L));
        User author = new User();
        Set<Theme> themes = new HashSet<>(Arrays.asList(new Theme(), new Theme()));

        when(mockUserRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(mockThemeRepository.findAllById(themeIds)).thenReturn(new ArrayList<>(themes));
        when(mockArticleRepository.save(any(Article.class))).thenReturn(newArticle);

        Article result = articleServiceUnderTest.createArticle(newArticle, authorId, themeIds);

        assertNotNull(result);
        assertEquals(author, result.getAuthor());
        assertEquals(themes, result.getThemes());
        verify(mockUserRepository, times(1)).findById(authorId);
        verify(mockThemeRepository, times(1)).findAllById(themeIds);
        verify(mockArticleRepository, times(1)).save(newArticle);
    }


    @Test
    void whenRequestAnArticleByIdOnArticleService_ThenReturnTheRetrievedArticleWithCorrectId() {
        Article storedArticle = new Article();
        storedArticle.setId(10L);

        when(mockArticleRepository.findById(10L)).thenReturn(Optional.of(storedArticle));

        Optional<Article> result = articleServiceUnderTest.getArticleById("10");

        assertNotNull(result);
        assertEquals(storedArticle.getId(), 10L);
    }
}
