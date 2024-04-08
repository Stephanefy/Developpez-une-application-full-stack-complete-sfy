package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.domain.dtos.comment.CommentDTO;
import com.openclassrooms.mddapi.domain.dtos.comment.CreateCommentDTO;
import com.openclassrooms.mddapi.domain.models.Comment;
import com.openclassrooms.mddapi.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
@Log4j2
public class CommentController {

    private ModelMapper modelMapper;

    @Autowired
    private CommentService commentService;

    public CommentController() {
        this.modelMapper = new ModelMapper();
    }

    // Get all comments for a specific article
    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByArticleId(@PathVariable String articleId) {
        List<Comment> comments = commentService.getCommentsByArticleId(Long.valueOf(articleId));

        List<CommentDTO> commentDtos = comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(commentDtos);
    }

    // Add a new comment to an article
    @PostMapping("/article/{articleId}")
    public ResponseEntity<CommentDTO> addCommentToArticle(@PathVariable Long articleId,
                                                          @Valid @RequestBody CreateCommentDTO commentDto) {
        log.info("Adding comment to article {}: {}", articleId, commentDto);

        Comment commentRequest = modelMapper.map(commentDto, Comment.class);
        Comment comment = commentService.addCommentToArticle(articleId, Long.valueOf(commentDto.getAuthorId()), commentRequest);

        return ResponseEntity.ok().body(modelMapper.map(comment, CommentDTO.class));
    }

    // Update a comment
    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id,
                                                    @Valid @RequestBody CommentDTO commentDto) {
        log.info("Updating comment {}: {}", id, commentDto);

        Comment commentRequest = modelMapper.map(commentDto, Comment.class);
        Comment updatedComment = commentService.updateComment(id, commentRequest);

        if (updatedComment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(modelMapper.map(updatedComment, CommentDTO.class));
    }

    // Delete a comment
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) throws Exception {
        log.info("Deleting comment {}", id);

        boolean isDeleted = commentService.deleteComment(Long.valueOf(id));

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
