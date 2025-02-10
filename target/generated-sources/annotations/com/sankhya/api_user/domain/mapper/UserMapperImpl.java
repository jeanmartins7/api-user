package com.sankhya.api_user.domain.mapper;

import com.sankhya.api_user.application.dto.UserRequestDTO;
import com.sankhya.api_user.application.dto.UserResponseDTO;
import com.sankhya.api_user.domain.User;
import com.sankhya.api_user.domain.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-10T09:45:19-0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User entityToUser(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        String username = null;
        String email = null;
        String name = null;
        String password = null;

        id = entity.getId();
        username = entity.getUsername();
        email = entity.getEmail();
        name = entity.getName();
        password = entity.getPassword();

        User user = new User( id, username, email, name, password );

        return user;
    }

    @Override
    public UserResponseDTO userToUserResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId( user.getId() );
        userResponseDTO.setUsername( user.getUsername() );
        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setName( user.getName() );

        return userResponseDTO;
    }

    @Override
    public UserEntity userToUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.username( user.getUsername() );
        userEntity.email( user.getEmail() );
        userEntity.name( user.getName() );
        userEntity.password( user.getPassword() );

        return userEntity.build();
    }

    @Override
    public User userToUserRequest(UserRequestDTO entity) {
        if ( entity == null ) {
            return null;
        }

        String username = null;
        String email = null;
        String name = null;
        String password = null;

        username = entity.getUsername();
        email = entity.getEmail();
        name = entity.getName();
        password = entity.getPassword();

        String id = null;

        User user = new User( id, username, email, name, password );

        return user;
    }
}
