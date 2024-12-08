package com.revolution.AuthService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
class User {

    private Long id;
    private String nickname;
    private String email;
    private String password;
    private Set<String> roles;

    static User withDefaultRole(String nickname, String email, String password) {
        return new User(null, nickname, email, password, Set.of("USER"));
    }
}
