package com.revolution.AuthService.domain.adapters

import com.revolution.AuthService.api.port.UserRepository
import com.revolution.AuthService.api.vo.UserVO

class TestUserRepository implements UserRepository {

    protected Map<Long, UserVO> database = new HashMap<>()

    @Override
    Optional<UserVO> findByEmail(String email) {
        database.values().stream()
            .filter { Objects.equals(it.email(), email)}
            .findFirst()
    }

    @Override
    boolean existsByEmail(String email) {
        database.values().stream()
            .anyMatch {Objects.equals(it.email(), email)}
    }

    @Override
    boolean existsByNickname(String nickname) {
        database.values().stream()
                .anyMatch {Objects.equals(it.nickname(), nickname)}
    }

    @Override
    UserVO save(UserVO user) {
        database.put(database.size(), user)
        user
    }
}
