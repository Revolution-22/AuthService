package com.revolution.AuthService.infrastructure.database;

import com.revolution.AuthService.api.port.UserRepository;
import com.revolution.AuthService.api.vo.UserVO;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<UserVO> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(entityMapper::toVO);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userJpaRepository.existsByNickname(nickname);
    }

    @Override
    public UserVO save(UserVO user) {
        return entityMapper.toVO(userJpaRepository.save(entityMapper.toEntity(user)));
    }
}
