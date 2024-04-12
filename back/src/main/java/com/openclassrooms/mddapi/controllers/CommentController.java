package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.domain.dtos.comment.CommentDTO;
import com.openclassrooms.mddapi.domain.dtos.comment.CreateCommentDTO;
import com.openclassrooms.mddapi.domain.models.Comment;
import com.openclassrooms.mddapi.services.CommentService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    private ModelMapper modelMapper;

    @Autowired
    private CommentService commentService;

    public CommentController() {
        this.modelMapper = new ModelMapper();
    }

    @PostMapping("/article/{articleId}")
    public ResponseEntity<CommentDTO> addCommentToArticle(@PathVariable Long articleId,
                                                          @Valid @RequestBody CreateCommentDTO commentDto) {
        log.info("Adding comment to article {}: {}", articleId, commentDto);

        Comment comment = commentService.addCommentToArticle(articleId, Long.valueOf(commentDto.getAuthorId()), commentDto.getContent());

        return ResponseEntity.ok().body(modelMapper.map(comment, CommentDTO.class));
    }

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
