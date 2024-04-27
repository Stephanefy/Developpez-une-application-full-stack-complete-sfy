package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.domains.models.Article;
import com.openclassrooms.mddapi.domains.models.Comment;
import com.openclassrooms.mddapi.domains.models.User;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.services.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log4j2
public class CommentServiceImpl implements CommentService {


    CommentRepository commentRepository;

    UserRepository userRepository;

    ArticleRepository articleRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public Comment addCommentToArticle(Long articleId, Long authorId, String content) {

        log.info("comment {}", content);

        Optional<User> authorOptional = userRepository.findById(authorId);
        User author = authorOptional.orElseThrow(() -> new NotFoundException("User not found"));

        Optional<Article> articleOptional = articleRepository.findById(articleId);
        Article article = articleOptional.orElseThrow(() -> new NotFoundException("Article not found"));

        Comment newComment = new Comment();
        newComment.setContent(content);

        newComment.setAuthor(author);
        newComment.setArticle(article);

        commentRepository.save(newComment);
        article.getComments().add(newComment);


        articleRepository.save(article);


        return newComment;
    }


    @Override
    public Comment getCommentById(Long id) {
        return null;
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        return null;
    }

    @Override
    public boolean deleteComment(Long id) {
       commentRepository.deleteById(id);

       return true;
    }
}
