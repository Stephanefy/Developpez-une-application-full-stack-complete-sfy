package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.domains.dtos.comment.CommentDTO;
import com.openclassrooms.mddapi.domains.dtos.comment.CreateCommentDTO;
import com.openclassrooms.mddapi.domains.models.Comment;
import com.openclassrooms.mddapi.services.CommentService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
@Log4j2
public class CommentController {

    private ModelMapper modelMapper;

    @Autowired
    private CommentService commentService;

    public CommentController(ModelMapper modelMapper, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.commentService = commentService;
    }

    /**
     * Adds a comment to an article.
     *
     * @param  articleId    The ID of the article to add the comment to
     * @param  commentDto   The data transfer object containing the comment details
     * @return              ResponseEntity containing the added CommentDTO
     */
    @PostMapping("/article/{articleId}")
    public ResponseEntity<CommentDTO> addCommentToArticle(@PathVariable Long articleId,
                                                          @Valid @RequestBody CreateCommentDTO commentDto) {
        log.info("Adding comment to article {}: {}", articleId, commentDto);

        Comment comment = commentService.addCommentToArticle(articleId, Long.valueOf(commentDto.getAuthorId()), commentDto.getContent());

        return ResponseEntity.ok().body(modelMapper.map(comment, CommentDTO.class));
    }

    /**
     * Updates a comment with the given ID.
     *
     * @param  id          The ID of the comment to update
     * @param  commentDto  The data transfer object containing the updated comment details
     * @return             ResponseEntity containing the updated CommentDTO
     */
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

    /**
     * Deletes a comment with the given ID.
     *
     * @param  id  The ID of the comment to delete
     * @return     A ResponseEntity indicating the outcome of the deletion
     */
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
