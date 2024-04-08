package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.domain.models.Theme;
import com.openclassrooms.mddapi.domain.models.User;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(User createUser) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("No user found"));

        return user;
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public Set<Theme> getUserSubscriptions(Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("No user found"));

        Set<Theme> subscriptions = user.getSubscriptions();

        return subscriptions;
    }


}
