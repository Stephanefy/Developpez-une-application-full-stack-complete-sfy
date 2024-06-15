package com.openclassrooms.mddapi.services;


import com.openclassrooms.mddapi.domains.models.Article;
import com.openclassrooms.mddapi.domains.models.Comment;
import com.openclassrooms.mddapi.domains.models.User;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.services.impl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    private CommentServiceImpl commentServiceUnderTest;

    @Mock
    private CommentRepository mockCommentRepository;

    @Mock
    private ArticleRepository mockArticleRepository;

    @Mock
    private UserRepository mockUserRepository;


    @Test
    void whenRequestAddingAComment_ThenReturnAddedComment() {
        Comment newComment = new Comment();
        newComment.setContent("test");
        Article storedArticle = new Article();
        User author = new User();
        Long authorId = 2L;
        Long articleId = 3L;

        when(mockUserRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(mockArticleRepository.findById(articleId)).thenReturn(Optional.of(storedArticle));
        when(mockCommentRepository.save(ArgumentMatchers.any(Comment.class))).thenReturn(newComment);

        Comment result = commentServiceUnderTest.addCommentToArticle(articleId, authorId, "test");

        assertNotNull(result);
        assertEquals(newComment.getContent(), result.getContent());
    }
}
