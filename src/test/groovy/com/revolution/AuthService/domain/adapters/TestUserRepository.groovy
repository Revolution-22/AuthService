package com.revolution.AuthService.domain.adapters

import com.revolution.AuthService.api.port.UserRepository
import com.revolution.AuthService.api.dto.UserDto

class TestUserRepository implements UserRepository {

    protected Map<Long, UserDto> database = new HashMap<>()

    @Override
    Optional<UserDto> findByEmail(String email) {
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
    UserDto save(UserDto user) {
        database.put(database.size(), user)
        user
    }
}
