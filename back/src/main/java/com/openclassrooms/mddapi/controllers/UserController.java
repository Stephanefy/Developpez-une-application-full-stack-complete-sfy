package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.domains.dtos.theme.ThemeDTO;
import com.openclassrooms.mddapi.domains.dtos.user.CreateUserDTO;
import com.openclassrooms.mddapi.domains.dtos.user.UpdateDTO;
import com.openclassrooms.mddapi.domains.dtos.user.UserDTO;
import com.openclassrooms.mddapi.domains.models.User;
import com.openclassrooms.mddapi.services.ThemeService;
import com.openclassrooms.mddapi.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    private ModelMapper modelMapper;

    private UserService userService;

    private ThemeService themeService;


    public UserController(ModelMapper modelMapper, UserService userService, ThemeService themeService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.themeService = themeService;
    }

    /**
     * Register a new user with the provided user data.
     *
     * @param  createUserDto    The data transfer object containing user registration details
     * @return                  ResponseEntity containing the registered UserDTO
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody CreateUserDTO createUserDto) {
        log.info("Registering new user: {}", createUserDto);

        User userRequest = modelMapper.map(createUserDto, User.class);
        User registeredUser = userService.createUser(userRequest);

        UserDTO responseBody = modelMapper.map(registeredUser, UserDTO.class);
        return ResponseEntity.ok().body(responseBody);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param  id   The ID of the user
     * @return      ResponseEntity containing the UserDTO of the retrieved user
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        User user = userService.getUserById(Long.valueOf(id));
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(modelMapper.map(user, UserDTO.class));
    }

    /**
     * Updates a user with the given ID.
     *
     * @param  id            The ID of the user to update
     * @param  updateUserDto The data transfer object containing the updated user details
     * @return               ResponseEntity containing the updated UserDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @Valid @RequestBody UpdateDTO updateUserDto) {
        log.info("Updating user {}: {}", id, updateUserDto);

        User userRequest = modelMapper.map(updateUserDto, User.class);

        log.info("after mapping {}", userRequest);
        User updatedUser = userService.updateUser(Long.valueOf(id), userRequest);

        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(modelMapper.map(updatedUser, UserDTO.class));
    }
    /**
     * Deletes a user with the given ID.
     *
     * @param  id  The ID of the user to delete
     * @return     A ResponseEntity indicating the outcome of the deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Deleting user {}", id);

        boolean isDeleted = userService.deleteUser(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
    /**
     * Retrieves the subscriptions for a user.
     *
     * @param  userId  The ID of the user
     * @return         A ResponseEntity containing the user's subscriptions
     */
    @GetMapping("/subscriptions/{userId}")
    public ResponseEntity<?> getUserSubscriptions(@PathVariable String userId) {

        Set<ThemeDTO> subscriptions = userService.getUserSubscriptions(Long.valueOf(userId)).stream()
                .map(theme -> modelMapper.map(theme, ThemeDTO.class))
                .collect(Collectors.toSet());;



        return ResponseEntity.ok().body(subscriptions);
    }

    /**
     * Subscribes a user to a theme.
     *
     * @param  themeId  The ID of the theme to subscribe to
     * @param  userId   The ID of the user subscribing
     * @return          A ResponseEntity indicating the outcome of the subscription
     */
    @PostMapping("/{id}/subscribe/{userId}")
    public ResponseEntity<?> subscribeToTheme(@PathVariable ("id") String themeId, @PathVariable ("userId") String userId) {

        themeService.subscribe(Long.valueOf(themeId), Long.valueOf(userId));

        return ResponseEntity.ok().build();
    }

    /**
     * Unsubscribes a user from a theme.
     *
     * @param  themeId  The ID of the theme to unsubscribe from
     * @param  userId   The ID of the user unsubscribing
     * @return          A ResponseEntity indicating the outcome of the unsubscription
     */
    @DeleteMapping("/{id}/unsubscribe/{userId}")
    public ResponseEntity<?> unsubscribeToTheme(@PathVariable ("id") String themeId, @PathVariable ("userId") String userId) {

        themeService.unsubscribe(Long.valueOf(themeId), Long.valueOf(userId));

        return ResponseEntity.ok().build();
    }
}
