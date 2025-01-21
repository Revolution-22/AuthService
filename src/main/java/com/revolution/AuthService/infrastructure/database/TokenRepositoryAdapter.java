package com.revolution.AuthService.infrastructure.database;

import com.revolution.AuthService.api.port.TokenRepository;
import com.revolution.AuthService.api.dto.TokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class TokenRepositoryAdapter implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<TokenDto> findByEmail(final String email) {
        return tokenJpaRepository.findByEmail(email)
                .map(entityMapper::toVO);
    }

    @Override
    public TokenDto save(final TokenDto tokenDto) {
        return entityMapper.toVO(tokenJpaRepository.save(entityMapper.toEntity(tokenDto)));
    }

    @Override
    public Optional<TokenDto> findByToken(final String token) {
        return tokenJpaRepository.findByToken(token)
                .map(entityMapper::toVO);
    }
}
