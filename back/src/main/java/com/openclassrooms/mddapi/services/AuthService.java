package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.domains.models.User;

public interface AuthService {

    User register(User user);

}
