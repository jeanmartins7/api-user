package com.sankhya.api_user.application.usecase.users;

import com.sankhya.api_user.application.dto.UserRequestDTO;
import com.sankhya.api_user.application.dto.UserResponseDTO;
import com.sankhya.api_user.domain.User;
import com.sankhya.api_user.domain.UserRepository;
import com.sankhya.api_user.domain.mapper.UserMapper;
import com.sankhya.api_user.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    public UserResponseDTO execute(final UserRequestDTO request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Usuário com e-mail " + request.getEmail() + " já cadastrado.");
        }

        final User user = mapper.userToUserRequest(request);
        final User savedUserEntity = mapper.entityToUser(userRepository.save(mapper.userToUserEntity(user)));

        return mapper.userToUserResponseDTO(savedUserEntity);
    }
}