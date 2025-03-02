package com.revolution.auth.service.infrastructure.database;

import com.revolution.auth.service.api.port.UserRepository;
import com.revolution.auth.service.api.dto.UserDto;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<UserDto> findByEmail(final String email) {
        return userJpaRepository.findByEmail(email)
                .map(entityMapper::toDto);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(final String nickname) {
        return userJpaRepository.existsByNickname(nickname);
    }

    @Override
    public UserDto save(final UserDto user) {
        return entityMapper.toDto(userJpaRepository.save(entityMapper.toEntity(user)));
    }
}
