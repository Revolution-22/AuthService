package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.AuthService;
import com.revolution.AuthService.api.port.Encoder;
import com.revolution.AuthService.api.port.TokenService;
import com.revolution.AuthService.api.port.UserRepository;
import com.revolution.AuthService.api.exception.AuthorizationException;
import com.revolution.AuthService.api.exception.UserAlreadyExistsException;
import com.revolution.AuthService.api.response.UserResponse;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class CoreAuthService implements AuthService {

    private final UserRepository userRepository;
    private final Encoder encoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Override
    public UserResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .map(userMapper::toModel)
                .orElseThrow(() -> new AuthorizationException(email));
        if(!encoder.matches(password, user.getPassword())) {
            throw new AuthorizationException(email);
        }
        return userMapper.toResponse(user, tokenService.generateToken(user.getEmail()));
    }

    @Override
    public UserResponse register(String nickname, String email, String password) {
        if (userRepository.existsByEmail(email) || userRepository.existsByNickname(nickname)) {
            throw new UserAlreadyExistsException(email, nickname);
        }
        String encodedPassword = encoder.encode(password);
        User user = User.withDefaultRole(nickname, email, encodedPassword);
        userRepository.save(userMapper.toVO(user));
        return userMapper.toResponse(user, tokenService.generateToken(user.getEmail()));
    }

    @Override
    public UserResponse validateToken(String token) {
        Optional<String> emailOptional = tokenService.getEmailByToken(token);
        if (emailOptional.isEmpty()) {
            throw new AuthorizationException("UNKNOWN USER");
        }
        User user = userRepository.findByEmail(emailOptional.get())
                .map(userMapper::toModel)
                .orElseThrow(() -> new AuthorizationException(emailOptional.get()));
        return userMapper.toResponse(user, tokenService.generateToken(user.getEmail()));
    }
}
