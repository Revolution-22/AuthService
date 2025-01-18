package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.port.RefreshTokenRepository;
import com.revolution.AuthService.api.port.TokenRepository;
import com.revolution.AuthService.api.port.TokenService;
import com.revolution.AuthService.api.response.TokenRefreshResponse;
import com.revolution.AuthService.api.vo.RefreshTokenVO;
import com.revolution.AuthService.api.vo.TokenVO;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class CoreTokenService implements TokenService {

    private final TokenRepository tokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenMapper tokenMapper;

    @Override
    public String generateToken(final String email) {
        Optional<Token> optionalToken = tokenRepository.findByEmail(email)
                .map(tokenMapper::toModel);
        if(optionalToken.isEmpty()) {
            Token token = Token.of(email);
            TokenVO savedTokenVO = tokenRepository.save(tokenMapper.toVO(token));
            return savedTokenVO.token();
        } else {
            Token token = optionalToken.get();
            token.refresh();
            tokenRepository.save(tokenMapper.toVO(token));
            return token.getToken();
        }
    }

    @Override
    public Optional<String> getEmailByToken(final String token) {
        return tokenRepository.findByToken(token)
                .map(tokenMapper::toModel)
                .map(target -> target.isExpired() ? null : target.getEmail());
    }

    @Override
    public String generateRefreshToken(final String email) {
        Optional<RefreshToken> optionalToken = refreshTokenRepository.findByEmail(email)
                .map(tokenMapper::toModel);
        if(optionalToken.isEmpty()) {
            RefreshToken token = RefreshToken.of(email);
            RefreshTokenVO savedTokenVO = refreshTokenRepository.save(tokenMapper.toVO(token));
            return savedTokenVO.token();
        } else {
            RefreshToken token = optionalToken.get();
            if (token.isExpired()) {
                token.refresh();
                refreshTokenRepository.save(tokenMapper.toVO(token));
            }
            return token.getToken();
        }
    }

    @Override
    public Optional<RefreshTokenVO> getRefreshTokenByEmail(final String email) {
        return refreshTokenRepository.findByEmail(email);
    }

    @Override
    public Optional<TokenRefreshResponse> refreshToken(final String refreshToken) {
        Optional<RefreshToken> optionalToken = refreshTokenRepository.findByToken(refreshToken)
                .map(tokenMapper::toModel);
        if(optionalToken.isEmpty()) {
            return Optional.empty();
        } else {
            RefreshToken token = optionalToken.get();
            token.refresh();
            refreshTokenRepository.save(tokenMapper.toVO(token));
            return Optional.of(new TokenRefreshResponse(token.getEmail(), generateToken(token.getEmail()), token.getToken()));
        }
    }
}
