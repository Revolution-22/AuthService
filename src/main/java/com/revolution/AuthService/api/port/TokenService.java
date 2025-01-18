package com.revolution.AuthService.api.port;

import com.revolution.AuthService.api.response.TokenRefreshResponse;
import com.revolution.AuthService.api.vo.RefreshTokenVO;

import java.util.Optional;

public interface TokenService {

    String generateToken(String email);

    Optional<String> getEmailByToken(String token);

    String generateRefreshToken(String email);

    Optional<RefreshTokenVO> getRefreshTokenByEmail(String email);

    Optional<TokenRefreshResponse> refreshToken(String token);
}
