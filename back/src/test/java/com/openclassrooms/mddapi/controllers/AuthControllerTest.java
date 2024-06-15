package com.openclassrooms.mddapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mddapi.domains.dtos.user.CreateUserDTO;
import com.openclassrooms.mddapi.domains.dtos.user.LoginUserDTO;
import com.openclassrooms.mddapi.domains.models.User;
import com.openclassrooms.mddapi.exceptions.NotFoundException;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.security.JWTUtils;
import com.openclassrooms.mddapi.services.AuthService;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.utils.MockUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AuthController authControllerUnderTest;

    @Mock
    private AuthenticationManager mockAuthenticationManager;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserService mockUserService;

    @Mock
    private AuthService mockAuthService;

    @Mock
    private Authentication mockAuthentication;

    @Mock
    private LoginUserDTO mockLoginUserDto;

    @Mock
    private CreateUserDTO mockCreateUserDto;

    @Mock
    private JWTUtils mockJwtUtils;

    @Mock
    private User mockUser;

    private String mockJWT;
    @Mock
    private MockUserDetails mockUserDetails;

    @Mock
    private ModelMapper modelMapper;


    private final String secretKey = "myVeryStrongSecretKey123456789012345";


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authControllerUnderTest).build();

//        mockUser = new User();
//        mockUser.setEmail("user@example.com");

        mockUserDetails = new MockUserDetails(
            1L,
                "johndoe",
                "John",
                "user@example.com",
                "Doe",
                true
        );

        mockLoginUserDto = LoginUserDTO.builder().usernameOrEmail("johndoe").password("password").build();
        mockJWT = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNzI1NDE1MTQ2LCJleHAiOjE3MjU0NTExNDZ9.VGnv5QyLx1D7r9iOY0f1J8R-lFQuGwM8I98mBQ3FijI";

        mockUser = new User();

        mockUser.setId(1L);
        mockUser.setUsername("johndoe");
        mockUser.setEmail("user@example.com");
        mockUser.setPassword("password");

        mockCreateUserDto = new CreateUserDTO();
        mockCreateUserDto.setUsername("johndoe");
        mockCreateUserDto.setEmail("user@example.com");
        mockCreateUserDto.setPassword("password");

    }

    @Test
    void whenUserLogin_AndSucceed_ThenReturnJWTResponseWithCorrectTokenSubject() throws Exception{


        when(mockUserService.getUserByUsername(anyString())).thenReturn(mockUser);
        when(mockUserService.getUserByEmail(anyString())).thenReturn(mockUser);
        when(mockAuthenticationManager.authenticate(any(Authentication.class))).thenReturn(mockAuthentication);

        // Mock the static method generateToken of JWTUtils
        try (var utilities = mockStatic(JWTUtils.class)) {
            utilities.when(() -> JWTUtils.generateToken(anyString(), anyString(), anyLong())).thenReturn(mockJWT);
        }
        MvcResult result = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mockLoginUserDto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andReturn();

        // Extract the token from the response
        String responseContent = result.getResponse().getContentAsString();
        String returnedToken = new ObjectMapper().readTree(responseContent).get("token").asText();

        // Validate the token
        String extractedSubject = JWTUtils.extractSubject(returnedToken);

        assertNotNull(extractedSubject);
        assertEquals("user@example.com", extractedSubject);

    }

    @Test
    void whenUserLogin_AndUserIsNotFound_ThenReturnNotFound() throws Exception {

        when(mockUserService.getUserByUsername(anyString())).thenThrow(NotFoundException.class);

       mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockLoginUserDto)))
                .andExpect(status().isNotFound());


    }


}
