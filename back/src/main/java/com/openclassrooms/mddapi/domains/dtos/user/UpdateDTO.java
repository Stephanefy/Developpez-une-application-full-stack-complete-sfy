package com.openclassrooms.mddapi.domains.dtos.user;


import lombok.Data;

@Data
public class UpdateDTO {
    private String username;
    private String email;
}
