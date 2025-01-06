package com.revolution.AuthService.infrastructure.adapter;

import com.revolution.AuthService.api.port.TokenService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class CoreTokenService implements TokenService {

    //TODO: Refresh system (Experience time of token)

    private final Map<String, String> tokenStorage = new HashMap<>();

    @Override
    public String generateToken(String username) {
        tokenStorage.computeIfAbsent(username, k -> UUID.randomUUID().toString());
        return tokenStorage.get(username);
    }

    @Override
    public Optional<String> getEmailByToken(String token) {
        return tokenStorage.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), token))
                .findFirst()
                .map(Map.Entry::getKey);
    }
}
