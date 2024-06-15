package com.openclassrooms.mddapi.domains.dtos.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {

    private String usernameOrEmail;
    private String password;
}
