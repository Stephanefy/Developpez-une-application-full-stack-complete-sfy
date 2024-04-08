package com.openclassrooms.mddapi.domain.dtos.comment;

import com.openclassrooms.mddapi.domain.dtos.user.UserDTO;
import lombok.Data;


@Data
public class CommentDTO {
    private Long id;
    private String content;
    private String publicationDate;
    private UserDTO author;

    // Constructors, Getters, and Setters
}
