package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.port.AuthService;
import com.revolution.AuthService.api.exception.AuthorizationException;
import com.revolution.AuthService.api.exception.UserAlreadyExistsException;
import com.revolution.AuthService.api.port.Encoder;
import com.revolution.AuthService.api.port.TokenService;
import com.revolution.AuthService.api.port.UserRepository;
import com.revolution.AuthService.api.dto.TokensRefreshDto;
import com.revolution.AuthService.api.response.UserResponse;
import com.revolution.AuthService.api.dto.RefreshTokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class CoreAuthService implements AuthService {

    private final UserRepository userRepository;
    private final Encoder encoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Override
    public UserResponse login(final String email, final String password) {
        User user = userRepository.findByEmail(email)
                .map(userMapper::toModel)
                .orElseThrow(() -> new AuthorizationException(email));
        if(!encoder.matches(password, user.getPassword())) {
            throw new AuthorizationException(email);
        }
        return userMapper.toResponse(user, tokenService.generateToken(user.getEmail()), tokenService.generateRefreshToken(user.getEmail()));
    }

    @Override
    public UserResponse register(final String nickname, final String email, final String password) {
        if (userRepository.existsByEmail(email) || userRepository.existsByNickname(nickname)) {
            throw new UserAlreadyExistsException(email, nickname);
        }
        String encodedPassword = encoder.encode(password);
        User user = User.withDefaultRole(nickname, email, encodedPassword);
        userRepository.save(userMapper.toVO(user));
        return userMapper.toResponse(user, tokenService.generateToken(user.getEmail()), tokenService.generateRefreshToken(user.getEmail()));
    }

    @Override
    public UserResponse validateToken(final String token) {
        Optional<String> emailOptional = tokenService.getEmailByToken(token);
        if (emailOptional.isEmpty()) {
            throw new AuthorizationException("UNKNOWN USER");
        }
        String email = emailOptional.get();
        User user = userRepository.findByEmail(email)
                .map(userMapper::toModel)
                .orElseThrow(() -> new AuthorizationException(email));
        RefreshTokenDto refreshTokenDto = tokenService.getRefreshTokenByEmail(email)
                .orElseThrow(() -> new AuthorizationException(email));
        return userMapper.toResponse(user, token, refreshTokenDto.token());
    }

    @Override
    public UserResponse refreshToken(final String token) {
        TokensRefreshDto tokensRefreshDto = tokenService.refreshToken(token)
                .orElseThrow(() -> new AuthorizationException(token));
        String email = tokensRefreshDto.email();
        User user = userRepository.findByEmail(email)
                .map(userMapper::toModel)
                .orElseThrow(() -> new AuthorizationException(email));
        return userMapper.toResponse(user, tokensRefreshDto.token(), tokensRefreshDto.refreshToken());
    }
}
