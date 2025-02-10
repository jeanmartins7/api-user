package com.sankhya.api_user.application.usecase.users;

import com.sankhya.api_user.application.dto.UserResponseDTO;
import com.sankhya.api_user.domain.UserRepository;
import com.sankhya.api_user.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserByEmailUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    public UserResponseDTO execute(final String email) {

        return mapper.userToUserResponseDTO(
                userRepository.findByEmail(email)
                .map(mapper::entityToUser)
                .orElse(null));
    }
}
