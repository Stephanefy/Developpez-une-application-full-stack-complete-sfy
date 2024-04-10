package com.openclassrooms.mddapi.controllers;


import com.openclassrooms.mddapi.domain.dtos.user.CreateUserDTO;
import com.openclassrooms.mddapi.domain.dtos.user.LoginUserDTO;
import com.openclassrooms.mddapi.domain.models.User;
import com.openclassrooms.mddapi.responses.TokenResponse;
import com.openclassrooms.mddapi.security.JWTUtils;
import com.openclassrooms.mddapi.services.AuthService;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.utils.validation.EmailValidator;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@Log4j2
public class AuthController {

    private ModelMapper modelMapper;
    private EmailValidator emailValidator;


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;


    public AuthController() {
        this.modelMapper = new ModelMapper();
        this.emailValidator = new EmailValidator();
    }


    @PostMapping(path= "/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDTO loginDto) {
        if (!emailValidator.isValidEmail(loginDto.getUsernameOrEmail())) {
            User user = userService.getUserByUsername(loginDto.getUsernameOrEmail());
            loginDto.setUsernameOrEmail(user.getEmail());
        }
        String identifier = loginDto.getUsernameOrEmail();
        String password = loginDto.getPassword();


        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(identifier, password));
        } catch (BadCredentialsException e) {
            throw e;
        }


        User loggedInUser = userService.getUserByEmail(identifier);


        String token = JWTUtils.generateToken(loggedInUser.getEmail(), loggedInUser.getUsername(), loggedInUser.getId());
        TokenResponse tokenResponse = new TokenResponse(token);


        return ResponseEntity.ok(tokenResponse);
    }


    @PostMapping(path = "/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody CreateUserDTO registerDto)  {

        User convertedUserDto = convertToUserEntity(registerDto);
        User registeredUser = authService.register(convertedUserDto);
        String token = JWTUtils.generateToken(registeredUser.getEmail(), registeredUser.getUsername(), registeredUser.getId());
        TokenResponse tokenResponse = new TokenResponse(token);

        return ResponseEntity.ok(tokenResponse);
    }


    private CreateUserDTO convertToUserDto(User user) {
        CreateUserDTO registerDto = modelMapper.map(user, CreateUserDTO.class);

        return registerDto;
    }

    private User convertToUserEntity(CreateUserDTO registerDto) {

        User user = modelMapper.map(registerDto, User.class);


        return user;
    }
}
