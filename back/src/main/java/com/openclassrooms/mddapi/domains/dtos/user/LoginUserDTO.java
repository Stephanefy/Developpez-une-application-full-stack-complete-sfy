package com.openclassrooms.mddapi.domains.dtos.user;


import lombok.Data;

@Data
public class LoginUserDTO {

    private String usernameOrEmail;
    private String password;
}
