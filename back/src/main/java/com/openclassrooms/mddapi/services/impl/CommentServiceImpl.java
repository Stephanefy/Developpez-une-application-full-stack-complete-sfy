package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.domain.models.Article;
import com.openclassrooms.mddapi.domain.models.Comment;
import com.openclassrooms.mddapi.domain.models.User;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log4j2
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;


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
