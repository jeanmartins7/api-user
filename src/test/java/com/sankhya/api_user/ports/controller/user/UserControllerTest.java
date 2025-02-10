package com.sankhya.api_user.ports.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankhya.api_user.application.dto.UserRequestDTO;
import com.sankhya.api_user.application.dto.UserResponseDTO;
import com.sankhya.api_user.application.usecase.users.CreateUserUseCase;
import com.sankhya.api_user.application.usecase.users.FindUserByEmailUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private FindUserByEmailUseCase findUserByEmailUseCase;

    @InjectMocks
    private UserController userController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void shouldCreateUser() throws Exception {

        final UserRequestDTO requestDTO = new UserRequestDTO("testUser", "test@example.com", "Test Name", "password123");
        final UserResponseDTO responseDTO = new UserResponseDTO("1","testUser", "test@example.com", "Test Name");

        when(createUserUseCase.execute(any(UserRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

    @Test
    void shouldGetUserByEmail() throws Exception {
        final String email = "test@example.com";
        final UserResponseDTO responseDTO = new UserResponseDTO("1","testUser", email, "Test Name");

        when(findUserByEmailUseCase.execute(eq(email))).thenReturn(responseDTO);

        mockMvc.perform(get("/users")
                        .header("X-User-Email", email))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }
}
