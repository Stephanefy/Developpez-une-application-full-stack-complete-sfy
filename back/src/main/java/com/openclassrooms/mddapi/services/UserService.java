package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.domain.models.Theme;
import com.openclassrooms.mddapi.domain.models.User;

import java.util.Set;

public interface UserService {
    User createUser(User createUser);
    User getUserById(Long id);
    User updateUser(Long id, User updatedUser);
    boolean deleteUser(Long id);
    User getUserByUsername(String username);
    Set<Theme> getUserSubscriptions(Long userId);
}