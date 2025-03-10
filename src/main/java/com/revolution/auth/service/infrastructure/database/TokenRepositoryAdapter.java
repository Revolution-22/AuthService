package com.revolution.auth.service.infrastructure.database;

import com.revolution.auth.service.api.port.TokenRepository;
import com.revolution.auth.service.api.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class TokenRepositoryAdapter implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<TokenDto> findByEmail(final String email) {
        return tokenJpaRepository.findByEmail(email)
                .map(entityMapper::toDto);
    }

    @Override
    public TokenDto save(final TokenDto tokenDto) {
        return entityMapper.toDto(tokenJpaRepository.save(entityMapper.toEntity(tokenDto)));
    }

    @Override
    public Optional<TokenDto> findByToken(final String token) {
        return tokenJpaRepository.findByToken(token)
                .map(entityMapper::toDto);
    }
}
