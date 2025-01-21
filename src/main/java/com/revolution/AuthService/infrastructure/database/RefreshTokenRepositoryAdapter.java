package com.revolution.AuthService.infrastructure.database;

import com.revolution.AuthService.api.port.RefreshTokenRepository;
import com.revolution.AuthService.api.dto.RefreshTokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository tokenJpaRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<RefreshTokenDto> findByEmail(final String email) {
        return tokenJpaRepository.findByEmail(email)
                .map(entityMapper::toVO);
    }

    @Override
    public RefreshTokenDto save(final RefreshTokenDto tokenVO) {
        return entityMapper.toVO(tokenJpaRepository.save(entityMapper.toEntity(tokenVO)));
    }

    @Override
    public Optional<RefreshTokenDto> findByToken(final String token) {
        return tokenJpaRepository.findByToken(token)
                .map(entityMapper::toVO);
    }
}
