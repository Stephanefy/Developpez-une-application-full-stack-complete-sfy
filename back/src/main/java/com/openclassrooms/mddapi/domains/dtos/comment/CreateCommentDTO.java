package com.openclassrooms.mddapi.domains.dtos.comment;

import lombok.Data;

@Data
public class CreateCommentDTO {
    private String content;
    private String authorId;
}
