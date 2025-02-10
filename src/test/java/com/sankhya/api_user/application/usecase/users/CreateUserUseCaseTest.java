package com.sankhya.api_user.application.usecase.users;

import com.sankhya.api_user.application.dto.UserRequestDTO;
import com.sankhya.api_user.application.dto.UserResponseDTO;
import com.sankhya.api_user.domain.User;
import com.sankhya.api_user.domain.UserEntity;
import com.sankhya.api_user.domain.UserRepository;
import com.sankhya.api_user.domain.mapper.UserMapper;
import com.sankhya.api_user.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    private UserRequestDTO userRequestDTO;
    private User user;
    private UserResponseDTO userResponseDTO;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userRequestDTO = new UserRequestDTO("exampleUsername", "example@example.com", "Example Name", "password123");
        user = new User("1", "exampleUsername", "example@example.com", "Example Name", "password123");
        userEntity = new UserEntity("1", "exampleUsername", "example@example.com", "Example Name", "password123");
        userResponseDTO = new UserResponseDTO("1", "exampleUsername", "example@example.com", "Example Name");
    }

    @Test
    void shouldCreateUserSuccessfully() {

        when(userRepository.findByEmail(userRequestDTO.getEmail())).thenReturn(Optional.empty());
        when(userMapper.userToUserRequest(userRequestDTO)).thenReturn(user);
        when(userMapper.userToUserEntity(user)).thenReturn(userEntity);
        when(userRepository.save(any())).thenReturn(userEntity);
        when(userMapper.entityToUser(userEntity)).thenReturn(user);
        when(userMapper.userToUserResponseDTO(user)).thenReturn(userResponseDTO);


        final UserResponseDTO response = createUserUseCase.execute(userRequestDTO);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("1");
        assertThat(response.getEmail()).isEqualTo("example@example.com");

        verify(userRepository).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {

        when(userRepository.findByEmail(userRequestDTO.getEmail())).thenReturn(Optional.ofNullable(userEntity));

        assertThatThrownBy(() -> createUserUseCase.execute(userRequestDTO))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessage("Usuário com e-mail example@example.com já cadastrado.");

        verify(userRepository, never()).save(any());
    }
}
