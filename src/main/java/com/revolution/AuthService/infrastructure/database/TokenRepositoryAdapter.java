package com.revolution.AuthService.infrastructure.database;

import com.revolution.AuthService.api.port.TokenRepository;
import com.revolution.AuthService.api.vo.TokenVO;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class TokenRepositoryAdapter implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<TokenVO> findByEmail(String email) {
        return tokenJpaRepository.findByEmail(email)
                .map(entityMapper::toVO);
    }

    @Override
    public TokenVO save(TokenVO tokenVO) {
        return entityMapper.toVO(tokenJpaRepository.save(entityMapper.toEntity(tokenVO)));
    }

    @Override
    public Optional<TokenVO> findByToken(String token) {
        return tokenJpaRepository.findByToken(token)
                .map(entityMapper::toVO);
    }
}
