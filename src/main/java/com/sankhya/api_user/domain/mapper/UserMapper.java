package com.sankhya.api_user.domain.mapper;

import com.sankhya.api_user.application.dto.UserRequestDTO;
import com.sankhya.api_user.application.dto.UserResponseDTO;
import com.sankhya.api_user.domain.User;
import com.sankhya.api_user.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User entityToUser(final UserEntity entity);

    UserResponseDTO userToUserResponseDTO(final User user);

    @Mapping(target = "id", ignore = true)
    UserEntity userToUserEntity(final User user);

    User userToUserRequest(final UserRequestDTO entity);
}
