package com.revolution.AuthService.infrastructure.database;

import com.revolution.AuthService.api.port.RefreshTokenRepository;
import com.revolution.AuthService.api.vo.RefreshTokenVO;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository tokenJpaRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<RefreshTokenVO> findByEmail(final String email) {
        return tokenJpaRepository.findByEmail(email)
                .map(entityMapper::toVO);
    }

    @Override
    public RefreshTokenVO save(final RefreshTokenVO tokenVO) {
        return entityMapper.toVO(tokenJpaRepository.save(entityMapper.toEntity(tokenVO)));
    }

    @Override
    public Optional<RefreshTokenVO> findByToken(final String token) {
        return tokenJpaRepository.findByToken(token)
                .map(entityMapper::toVO);
    }
}
