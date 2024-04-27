package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.domains.models.Theme;
import com.openclassrooms.mddapi.domains.models.User;

import java.util.Set;

public interface UserService {
    User createUser(User createUser);
    User getUserById(Long id);
    User updateUser(Long id, User updatedUser);
    boolean deleteUser(Long id);
    User getUserByUsername(String username);

    User getUserByEmail(String email);
    Set<Theme> getUserSubscriptions(Long userId);
}
