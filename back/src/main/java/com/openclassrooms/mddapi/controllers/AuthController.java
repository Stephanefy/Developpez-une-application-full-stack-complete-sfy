package com.openclassrooms.mddapi.controllers;


import com.openclassrooms.mddapi.domains.dtos.user.CreateUserDTO;
import com.openclassrooms.mddapi.domains.dtos.user.LoginUserDTO;
import com.openclassrooms.mddapi.domains.models.User;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.domains.responses.TokenResponse;
import com.openclassrooms.mddapi.security.JWTUtils;
import com.openclassrooms.mddapi.services.AuthService;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.utils.validation.EmailValidatorUtils;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {


    private final ModelMapper modelMapper;

    private AuthenticationManager authenticationManager;

    private AuthService authService;

    private UserService userService;


    public AuthController(ModelMapper modelMapper, AuthenticationManager authenticationManager, AuthService authService, UserService userService) {
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userService = userService;
    }

    /**
     * Endpoint for user login.
     *
     * @param loginDto The DTO containing user login information
     * @return ResponseEntity containing the user's authentication token
     */
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDTO loginDto) {
        // Check if it's a username instead of an email
        if (!EmailValidatorUtils.isValidEmail(loginDto.getUsernameOrEmail())) {
            try {
                User user = userService.getUserByUsername(loginDto.getUsernameOrEmail());
                loginDto.setUsernameOrEmail(user.getEmail());
            } catch (NotFoundException e) {
                return ResponseEntity.notFound().build();

            }

        }
        String identifier = loginDto.getUsernameOrEmail();
        String password = loginDto.getPassword();


        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(identifier, password));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
        }


        User loggedInUser = userService.getUserByEmail(identifier);


        String token = JWTUtils.generateToken(loggedInUser.getEmail(), loggedInUser.getUsername(), loggedInUser.getId());
        TokenResponse tokenResponse = new TokenResponse(token);


        return ResponseEntity.ok(tokenResponse);
    }

    /**
     * Registers a new user using the provided user data and returns a ResponseEntity containing the user's authentication token.
     *
     * @param registerDto The DTO containing user registration information
     * @return ResponseEntity containing the user's authentication token
     */
    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody CreateUserDTO registerDto) {
        User convertedUserDto = convertToUserEntity(registerDto);

        try {
            User registeredUser = authService.register(convertedUserDto);
            String token = JWTUtils.generateToken(registeredUser.getEmail(), registeredUser.getUsername(), registeredUser.getId());
            TokenResponse tokenResponse = new TokenResponse(token);
            return ResponseEntity.ok(tokenResponse);
        } catch (Error e) {
            log.error("Registration error: ", e);
            return ResponseEntity.badRequest().body("L'email ou le nom d'utilisateur est déjà enregistré chez nous");
        }
    }

    /**
     * Retrieves a token for renewing the user's authentication.
     *
     * @param id The ID of the user
     * @return ResponseEntity containing the renewed token
     */
    @GetMapping(path = "/renew/{id}")
    public ResponseEntity<TokenResponse> renewToken(@PathVariable("id") String id) {

        User user = userService.getUserById(Long.valueOf(id));
        String token = JWTUtils.generateToken(user.getEmail(), user.getUsername(), user.getId());
        TokenResponse tokenResponse = new TokenResponse(token);

        return ResponseEntity.ok(tokenResponse);
    }

    /**
     * A method to convert a User object to a CreateUserDTO object.
     *
     * @param user The User object to be converted
     * @return The converted CreateUserDTO object
     */
    private CreateUserDTO convertToUserDto(User user) {
        CreateUserDTO registerDto = modelMapper.map(user, CreateUserDTO.class);

        return registerDto;
    }

    /**
     * A method to convert a CreateUserDTO object to a User object.
     *
     * @param registerDto The CreateUserDTO object to be converted
     * @return The converted User object
     */
    private User convertToUserEntity(CreateUserDTO registerDto) {

        User user = modelMapper.map(registerDto, User.class);


        return user;
    }
}
