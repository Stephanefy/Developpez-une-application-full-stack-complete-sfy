package com.openclassrooms.mddapi.controllers;


import com.openclassrooms.mddapi.domain.dtos.article.ArticleDTO;
import com.openclassrooms.mddapi.domain.dtos.article.CreateArticleDTO;
import com.openclassrooms.mddapi.domain.dtos.user.UserDTO;
import com.openclassrooms.mddapi.domain.models.Article;
import com.openclassrooms.mddapi.services.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/article")
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {


    private ModelMapper modelMapper;

    @Autowired
    private ArticleService articleService;


    public ArticleController() {
        this.modelMapper = new ModelMapper();
    }

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

    @PostMapping()
    public ResponseEntity<?> createArticle(@Valid @RequestBody CreateArticleDTO articleDto) {
        log.info("article themes {}",articleDto.getThemes());


        Article newArticle = modelMapper.map(articleDto, Article.class);

        Article createdArticle = articleService.createArticle(newArticle, articleDto.getAuthor(), articleDto.getThemes());
        URI location = URI.create("/api/article/" + createdArticle.getId());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable("id") String id, @Valid @RequestBody CreateArticleDTO articleDto) {
        try {
            Article updatedArticle = articleService.updateArticle(id, modelMapper.map(articleDto, Article.class));

            return ResponseEntity.ok().body(modelMapper.map(updatedArticle, ArticleDTO.class));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    //TODO: after implementing frontend comeback to this route
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
