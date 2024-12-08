package com.revolution.AuthService.api.port;

public interface Encoder {
    boolean matches(String rawPassword, String password);
    String encode(String rawPassword);
}
