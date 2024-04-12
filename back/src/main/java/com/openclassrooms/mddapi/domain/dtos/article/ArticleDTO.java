package com.openclassrooms.mddapi.domain.dtos.article;

import com.openclassrooms.mddapi.domain.dtos.comment.CommentDTO;
import com.openclassrooms.mddapi.domain.dtos.theme.ThemeDTO;
import com.openclassrooms.mddapi.domain.dtos.user.UserDTO;
import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String description;
    private UserDTO author;
    private Set<ThemeDTO> themes;
    private Set<CommentDTO> comments;
    private Date publicationDate;

}
