package com.revolution.auth.service.domain;

import com.revolution.auth.service.api.port.AuthService;
import com.revolution.auth.service.api.exception.AuthorizationException;
import com.revolution.auth.service.api.exception.UserAlreadyExistsException;
import com.revolution.auth.service.api.port.BrokerService;
import com.revolution.auth.service.api.port.Encoder;
import com.revolution.auth.service.api.port.TokenService;
import com.revolution.auth.service.api.port.UserRepository;
import com.revolution.auth.service.api.dto.TokensRefreshDto;
import com.revolution.common.response.UserResponse;
import com.revolution.auth.service.api.dto.RefreshTokenDto;
import lombok.RequiredArgsConstructor;

import com.revolution.common.event.RegisterEvent;

import java.util.Optional;

import static com.revolution.common.event.Topics.REGISTER_TOPIC;

@RequiredArgsConstructor
class CoreAuthService implements AuthService {

    private final UserRepository userRepository;
    private final Encoder encoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final BrokerService brokerService;

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
        User savedUser = userMapper.toModel(userRepository.save(userMapper.toDto(user)));
        brokerService.publishMessage(REGISTER_TOPIC, new RegisterEvent(savedUser.getId()));
        return userMapper.toResponse(savedUser, tokenService.generateToken(user.getEmail()), tokenService.generateRefreshToken(user.getEmail()));
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
