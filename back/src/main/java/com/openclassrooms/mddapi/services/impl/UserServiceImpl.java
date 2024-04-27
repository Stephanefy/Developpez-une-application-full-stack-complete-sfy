package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.domains.models.Theme;
import com.openclassrooms.mddapi.domains.models.User;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
@Log4j2
public class UserServiceImpl implements UserService {

    UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("No user found"));
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());

        userRepository.save(user);


        log.info("user email after update {}", user);

        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public Set<Theme> getUserSubscriptions(Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("No user found"));

        Set<Theme> subscriptions = user.getSubscriptions();

        return subscriptions;
    }


}
