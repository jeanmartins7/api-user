package com.sankhya.api_user.infrastructure.adapters;

import com.sankhya.api_user.domain.UserEntity;
import com.sankhya.api_user.domain.UserRepository;
import com.sankhya.api_user.infrastructure.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public UserEntity save(final UserEntity userEntity) {
        return jpaRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByEmail(final String email) {
        return jpaRepository.findByEmail(email);
    }
}