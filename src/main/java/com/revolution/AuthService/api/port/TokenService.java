package com.revolution.AuthService.api.port;

import java.util.Optional;

public interface TokenService {

    String generateToken(String username);

    Optional<String> getEmailByToken(String token);
}
