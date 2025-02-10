package com.sankhya.api_user.application.usecase.users;

import com.sankhya.api_user.application.dto.UserResponseDTO;
import com.sankhya.api_user.domain.User;
import com.sankhya.api_user.domain.UserEntity;
import com.sankhya.api_user.domain.UserRepository;
import com.sankhya.api_user.domain.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserByEmailUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private FindUserByEmailUseCase findUserByEmailUseCase;

    private UserEntity userEntity;
    private User user;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity("1", "testuser", "test@example.com", "Test User", "password");
        user = new User("1", "testuser", "test@example.com", "Test User", "password");
        userResponseDTO = new UserResponseDTO("1", "testuser", "test@example.com", "Test User");
    }

    @Test
    void shouldReturnUserWhenEmailExists() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(userEntity));
        when(mapper.entityToUser(userEntity)).thenReturn(user);
        when(mapper.userToUserResponseDTO(user)).thenReturn(userResponseDTO);

        final UserResponseDTO result = findUserByEmailUseCase.execute("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).findByEmail("test@example.com");
        verify(mapper, times(1)).entityToUser(userEntity);
        verify(mapper, times(1)).userToUserResponseDTO(user);
    }

    @Test
    void shouldReturnNullWhenEmailDoesNotExist() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        final UserResponseDTO result = findUserByEmailUseCase.execute("notfound@example.com");

        assertNull(result);
        verify(userRepository, times(1)).findByEmail("notfound@example.com");
    }
}