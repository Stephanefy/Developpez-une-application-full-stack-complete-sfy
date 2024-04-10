package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.domain.models.Theme;
import com.openclassrooms.mddapi.domain.models.User;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.ThemeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    ThemeRepository themeRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public Optional<Theme> getThemeById(String id) {
        return themeRepository.findById(Long.valueOf(id));
    }

    @Override
    public Theme updateTheme(String id, Theme theme) {
        return null;
    }

    @Override
    public void subscribe(Long themeId, Long userId) {
        Optional<Theme> optionalTheme = themeRepository.findById(themeId);
        Theme theme = optionalTheme.orElseThrow(() -> new NotFoundException("Theme not found"));

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("User not found"));

        log.info("is reached");
        // Add the user to the theme's subscribers list
        theme.getSubscribers().add(user);

        // Optionally, add the theme to the user's subscribed themes as well
        // to maintain bidirectional consistency
        user.getSubscriptions().add(theme);

        // Save the updated theme (and user if necessary) back to the database
        themeRepository.save(theme);
        userRepository.save(user); // Only needed if you're updating the user's subscribed themes

    }

    @Override
    public void unsubscribe(Long themeId, Long userId) {
        Optional<Theme> optionalTheme = themeRepository.findById(themeId);
        Theme theme = optionalTheme.orElseThrow(() -> new NotFoundException("Theme not found"));

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new NotFoundException("User not found"));

        // Check if the user is actually subscribed to the theme
        boolean removedFromTheme = theme.getSubscribers().remove(user);
        boolean removedFromUser = user.getSubscriptions().remove(theme);

        // Convert the set of subscriptions to a readable string format, e.g., theme IDs
        String subscriptionIds = user.getSubscriptions().stream()
                .map(t -> t.getId().toString())
                .collect(Collectors.joining(", "));

        log.info("User {} now subscribed to theme IDs: {}", user.getId(), subscriptionIds);

        // If the user was subscribed, save the updated entities
        if (removedFromTheme && removedFromUser) {
            themeRepository.save(theme);
            userRepository.save(user);
        } else {
            // Log or handle the case where the user wasn't subscribed
            // This is optional and depends on how you want to handle this scenario
            throw new RuntimeException("User was not subscribed to the theme");
        }
    }

    @Override
    public boolean deleteTheme(String id) {
        themeRepository.deleteById(Long.valueOf(id));

        return true;
    }
}
