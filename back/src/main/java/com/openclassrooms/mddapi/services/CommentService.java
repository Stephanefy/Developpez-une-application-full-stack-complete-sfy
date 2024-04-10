package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.domain.models.Comment;

public interface CommentService {
    Comment addCommentToArticle(Long articleId, Long authorId, String content);
//    List<Comment> getCommentsByArticleId(Long articleId);
    Comment getCommentById(Long id);
    Comment updateComment(Long id, Comment comment);
    boolean deleteComment(Long id);
}
