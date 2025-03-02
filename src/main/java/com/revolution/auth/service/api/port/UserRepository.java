package com.revolution.auth.service.api.port;

import com.revolution.auth.service.api.dto.UserDto;

import java.util.Optional;

public interface UserRepository {

    Optional<UserDto> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    UserDto save(UserDto user);
}
