package com.openclassrooms.mddapi.domains.dtos.comment;

import com.openclassrooms.mddapi.domains.dtos.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private String publicationDate;
    private UserDTO author;

    // Constructors, Getters, and Setters
}
