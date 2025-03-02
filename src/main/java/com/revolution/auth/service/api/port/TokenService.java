package com.revolution.auth.service.api.port;

import com.revolution.auth.service.api.dto.TokensRefreshDto;
import com.revolution.auth.service.api.dto.RefreshTokenDto;

import java.util.Optional;

public interface TokenService {

    String generateToken(String email);

    Optional<String> getEmailByToken(String token);

    String generateRefreshToken(String email);

    Optional<RefreshTokenDto> getRefreshTokenByEmail(String email);

    Optional<TokensRefreshDto> refreshToken(String token);
}
