package com.openclassrooms.mddapi.domain.dtos.user;


import lombok.Data;

@Data
public class UpdateDTO {
    private String username;
    private String email;
}
