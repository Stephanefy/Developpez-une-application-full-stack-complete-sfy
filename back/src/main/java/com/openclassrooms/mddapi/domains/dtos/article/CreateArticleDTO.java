package com.openclassrooms.mddapi.domains.dtos.article;

import lombok.Data;

import java.util.Set;


@Data
public class CreateArticleDTO {

    private String title;
    private String content;
    private String description;
    private Long author;
    private Set<Long> themes;
}
