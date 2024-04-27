package com.openclassrooms.mddapi.domains.dtos.user;


import lombok.Data;

@Data
public class CreateUserDTO {
    private String email;
    private String password;
    private String username;

}
