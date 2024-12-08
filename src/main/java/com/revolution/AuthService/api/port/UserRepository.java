package com.revolution.AuthService.api.port;

import com.revolution.AuthService.api.vo.UserVO;

import java.util.Optional;

public interface UserRepository {

    Optional<UserVO> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    UserVO save(UserVO user);
}
