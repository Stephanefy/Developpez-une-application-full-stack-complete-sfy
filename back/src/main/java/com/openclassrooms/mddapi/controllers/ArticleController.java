package com.openclassrooms.mddapi.controllers;


import com.openclassrooms.mddapi.domains.dtos.article.ArticleDTO;
import com.openclassrooms.mddapi.domains.dtos.article.CreateArticleDTO;
import com.openclassrooms.mddapi.domains.dtos.user.UserDTO;
import com.openclassrooms.mddapi.domains.models.Article;
import com.openclassrooms.mddapi.services.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
@Log4j2
public class ArticleController {


    private ModelMapper modelMapper;

    private ArticleService articleService;


    public ArticleController(ModelMapper modelMapper, ArticleService articleService) {
        this.modelMapper = modelMapper;
        this.articleService = articleService;
    }


    /**
     * Retrieves all articles, maps them to DTOs, and sets the author DTO if present.
     *
     * @return          ResponseEntity containing a list of ArticleDTOs
     */
    @GetMapping("")
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();


        List<ArticleDTO> articleDtos = articles.stream()
                .map(article -> {
                    ArticleDTO dto = modelMapper.map(article, ArticleDTO.class);
                    UserDTO authorDto = Optional.ofNullable(article.getAuthor())
                            .map(author -> modelMapper.map(author, UserDTO.class))
                            .orElse(null);
                    dto.setAuthor(authorDto);
                    return dto;
                })
                .collect(Collectors.toList());


        return ResponseEntity.ok().body(articleDtos);
    }


    /**
     * Retrieves all related articles to the subscription, maps them to DTOs, and sets the author DTO if present.
     *
     * @param  id  The ID of the subscription
     * @return     ResponseEntity containing a list of ArticleDTOs
     */
    @GetMapping("/subscribed/{id}")
    public ResponseEntity<List<ArticleDTO>> getAllRelatedArticlesToSubscription(@PathVariable("id") String id) {
        List<Article> subscribedThemesArticles = articleService.getAllRelatedThemeArticles(Long.valueOf(id));


        List<ArticleDTO> articleDtos = subscribedThemesArticles.stream()
                .map(article -> {
                    ArticleDTO dto = modelMapper.map(article, ArticleDTO.class);
                    UserDTO authorDto = Optional.ofNullable(article.getAuthor())
                            .map(author -> modelMapper.map(author, UserDTO.class))
                            .orElse(null);
                    dto.setAuthor(authorDto);
                    return dto;
                })
                .collect(Collectors.toList());


        return ResponseEntity.ok().body(articleDtos);
    }
    /**
     * Retrieves an article by its ID.
     *
     * @param  id  The ID of the article
     * @return     ResponseEntity containing the ArticleDTO of the retrieved article
     */
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable("id") String id) {

        try {
            Optional<Article> article = articleService.getArticleById(id);

            if (article == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(modelMapper.map(article, ArticleDTO.class));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * Creates a new article based on the provided data in the CreateArticleDTO.
     *
     * @param  articleDto    The data transfer object containing information for the new article
     * @return               ResponseEntity indicating the successful creation of the article
     */
    @PostMapping()
    public ResponseEntity<?> createArticle(@Valid @RequestBody CreateArticleDTO articleDto) {
        log.info("article themes {}",articleDto.getThemes());
        Article createdArticle;

        Article newArticle = modelMapper.map(articleDto, Article.class);
        try {
            createdArticle = articleService.createArticle(newArticle, articleDto.getAuthor(), articleDto.getThemes());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e);

        }
        URI location = URI.create("/api/article/" + createdArticle.getId());

        return ResponseEntity.created(location).build();
    }

    /**
     * Updates an article.
     *
     * @param  id          The ID of the article to update
     * @param  articleDto  The data transfer object containing updated information for the article
     * @return             ResponseEntity containing the updated ArticleDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable("id") String id, @Valid @RequestBody CreateArticleDTO articleDto) {
        try {
            Article updatedArticle = articleService.updateArticle(id, modelMapper.map(articleDto, Article.class));

            return ResponseEntity.ok().body(modelMapper.map(updatedArticle, ArticleDTO.class));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    

    /**
     * Deletes an article by its ID.
     *
     * @param  id  The ID of the article to delete
     * @return     ResponseEntity indicating the successful deletion of the article
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") String id) {
        try {
            Optional<Article> article = articleService.getArticleById(id);

            if (article == null) {
                return ResponseEntity.notFound().build();
            }

            articleService.deleteArticle(id);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
