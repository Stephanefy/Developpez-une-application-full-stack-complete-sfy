package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.domain.dtos.theme.ThemeDTO;
import com.openclassrooms.mddapi.domain.dtos.user.CreateUserDTO;
import com.openclassrooms.mddapi.domain.dtos.user.UserDTO;
import com.openclassrooms.mddapi.domain.models.User;
import com.openclassrooms.mddapi.services.ThemeService;
import com.openclassrooms.mddapi.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Log4j2
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ThemeService themeService;


    public UserController() {
        this.modelMapper = new ModelMapper();
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody CreateUserDTO createUserDto) {
        log.info("Registering new user: {}", createUserDto);

        User userRequest = modelMapper.map(createUserDto, User.class);
        User registeredUser = userService.createUser(userRequest);

        UserDTO responseBody = modelMapper.map(registeredUser, UserDTO.class);
        return ResponseEntity.ok().body(responseBody);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        User user = userService.getUserById(Long.valueOf(id));
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(modelMapper.map(user, UserDTO.class));
    }

    // Update user details
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO updateUserDto) {
        log.info("Updating user {}: {}", id, updateUserDto);

        User userRequest = modelMapper.map(updateUserDto, User.class);
        User updatedUser = userService.updateUser(id, userRequest);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(modelMapper.map(updatedUser, UserDTO.class));
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Deleting user {}", id);

        boolean isDeleted = userService.deleteUser(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscriptions/{userId}")
    public ResponseEntity<?> getUserSubscriptions(@PathVariable String userId) {

        Set<ThemeDTO> subscriptions = userService.getUserSubscriptions(Long.valueOf(userId)).stream()
                .map(theme -> modelMapper.map(theme, ThemeDTO.class))
                .collect(Collectors.toSet());;



        return ResponseEntity.ok().body(subscriptions);
    }

    @PostMapping("/{id}/subscribe/{userId}")
    public ResponseEntity<?> subscribeToTheme(@PathVariable ("id") String themeId, @PathVariable ("userId") String userId) {

        themeService.subscribe(Long.valueOf(themeId), Long.valueOf(userId));

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/unsubscribe/{userId}")
    public ResponseEntity<?> unsubscribeToTheme(@PathVariable ("id") String themeId, @PathVariable ("userId") String userId) {

        themeService.unsubscribe(Long.valueOf(themeId), Long.valueOf(userId));

        return ResponseEntity.ok().build();
    }
}
