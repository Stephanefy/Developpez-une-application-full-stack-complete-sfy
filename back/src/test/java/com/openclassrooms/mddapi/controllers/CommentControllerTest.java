package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.domains.dtos.comment.CommentDTO;
import com.openclassrooms.mddapi.domains.dtos.comment.CreateCommentDTO;
import com.openclassrooms.mddapi.domains.dtos.user.UserDTO;
import com.openclassrooms.mddapi.domains.models.Article;
import com.openclassrooms.mddapi.domains.models.Comment;
import com.openclassrooms.mddapi.services.CommentService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@Log4j2
@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @InjectMocks
    private CommentController commentControllerUnderTest;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CommentService mockCommentService;


    @Test
    void whenAddingANewCommentToAnArticle_ThenReturnTheCorrectCommentContent() {
        Comment newComment = new Comment();
        newComment.setArticle(new Article());
        newComment.setContent("Wow, this article is a total game-changer! I've never read anything so insightful and thought-provoking. The author's grasp of the subject matter is truly impressive, and their writing style is engaging and captivating. I can't wait to share this with all my friends and colleagues – they'll be blown away! Keep up the amazing work, author!");

        CreateCommentDTO newCommentDto = new CreateCommentDTO();
        newCommentDto.setContent("Wow, this article is a total game-changer! I've never read anything so insightful and thought-provoking. The author's grasp of the subject matter is truly impressive, and their writing style is engaging and captivating. I can't wait to share this with all my friends and colleagues – they'll be blown away! Keep up the amazing work, author!");
        newCommentDto.setAuthorId("2");

        when(mockCommentService.addCommentToArticle(anyLong(), anyLong(), anyString())).thenReturn(newComment);
        when(modelMapper.map(any(Comment.class), eq(CommentDTO.class))).thenReturn(new CommentDTO(
                2L,
                "Wow, this article is a total game-changer! I've never read anything so insightful and thought-provoking. The author's grasp of the subject matter is truly impressive, and their writing style is engaging and captivating. I can't wait to share this with all my friends and colleagues – they'll be blown away! Keep up the amazing work, author!",
                "2024/01/23",
                new UserDTO()
        ));


        ResponseEntity<CommentDTO> response = commentControllerUnderTest.addCommentToArticle(3L, newCommentDto);

        CommentDTO responseBody = response.getBody();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response);
        assertEquals("Wow, this article is a total game-changer! I've never read anything so insightful and thought-provoking. The author's grasp of the subject matter is truly impressive, and their writing style is engaging and captivating. I can't wait to share this with all my friends and colleagues – they'll be blown away! Keep up the amazing work, author!", responseBody.getContent());

    }

}