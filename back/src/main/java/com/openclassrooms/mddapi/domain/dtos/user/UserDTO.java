package com.openclassrooms.mddapi.domain.dtos.user;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;

}
