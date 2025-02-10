package com.sankhya.api_user.domain;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(final UserEntity userEntity);
    Optional<UserEntity> findByEmail(final String email);
}