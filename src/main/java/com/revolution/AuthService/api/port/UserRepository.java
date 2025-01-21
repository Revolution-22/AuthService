package com.revolution.AuthService.api.port;

import com.revolution.AuthService.api.dto.UserDto;

import java.util.Optional;

public interface UserRepository {

    Optional<UserDto> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    UserDto save(UserDto user);
}
