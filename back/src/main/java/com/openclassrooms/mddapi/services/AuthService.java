package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.domain.models.User;

public interface AuthService {

    User register(User user);

}
