package com.sankhya.api_user.domain.mapper;

import com.sankhya.api_user.application.dto.UserRequestDTO;
import com.sankhya.api_user.application.dto.UserResponseDTO;
import com.sankhya.api_user.domain.User;
import com.sankhya.api_user.domain.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper mapper = UserMapper.INSTANCE;

    @Test
    void shouldMapEntityToUser() {
        final UserEntity entity = new UserEntity();
        entity.setId("123");
        entity.setUsername("testUser");
        entity.setEmail("test@example.com");
        entity.setName("Test Name");
        entity.setPassword("password123");

        final User user = mapper.entityToUser(entity);

        assertNotNull(user);
        assertEquals(entity.getId(), user.getId());
        assertEquals(entity.getUsername(), user.getUsername());
        assertEquals(entity.getEmail(), user.getEmail());
        assertEquals(entity.getName(), user.getName());
        assertEquals(entity.getPassword(), user.getPassword());
    }

    @Test
    void shouldMapUserToUserResponseDTO() {
        final User user = new User("123", "testUser", "test@example.com", "Test Name", "password123");

        final UserResponseDTO dto = mapper.userToUserResponseDTO(user);

        assertNotNull(dto);
        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getName(), dto.getName());
    }

    @Test
    void shouldMapUserToUserEntity() {

        final User user = new User("123", "testUser", "test@example.com", "Test Name", "password123");

        final UserEntity entity = mapper.userToUserEntity(user);

        assertNotNull(entity);
        assertNull(entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getName(), entity.getName());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    void shouldMapUserRequestDTOToUser() {

        final UserRequestDTO dto = new UserRequestDTO("testUser", "test@example.com", "Test Name", "password123");

        final User user = mapper.userToUserRequest(dto);

        assertNotNull(user);
        assertNull(user.getId());
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getName(), user.getName());
        assertEquals(dto.getPassword(), user.getPassword());
    }
}
