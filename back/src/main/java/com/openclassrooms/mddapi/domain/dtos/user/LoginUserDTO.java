package com.openclassrooms.mddapi.domain.dtos.user;


import lombok.Data;

@Data
public class LoginUserDTO {

    private String usernameOrEmail;
    private String password;
}
