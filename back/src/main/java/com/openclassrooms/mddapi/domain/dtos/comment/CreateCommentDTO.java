package com.openclassrooms.mddapi.domain.dtos.comment;

import lombok.Data;

@Data
public class CreateCommentDTO {
    private String content;
    private String authorId;
}
