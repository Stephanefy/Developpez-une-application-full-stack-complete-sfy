package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.domains.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;


    @Test
    void whenRegisteringANewUser_EncodeThePlainPassword_ThenReturnTheRegisteredUser() {
        User user = new User();
        user.setPassword("plainPassword");
        user.setUsername("johndoe");
        user.setEmail("user@example.com");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setPassword("encodedPassword");
        savedUser.setUsername("johndoe");
        savedUser.setEmail("user@example.com");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = authService.register(user);

        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        assertNotEquals("plainPassword", result.getPassword());
        assertEquals("johndoe", result.getUsername());
        assertEquals("user@example.com", result.getEmail());
        verify(passwordEncoder, times(1)).encode("plainPassword");
        verify(userRepository, times(1)).save(user);
    }

}