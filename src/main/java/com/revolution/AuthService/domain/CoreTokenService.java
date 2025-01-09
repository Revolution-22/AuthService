package com.revolution.AuthService.domain;

import com.revolution.AuthService.api.port.TokenRepository;
import com.revolution.AuthService.api.port.TokenService;
import com.revolution.AuthService.api.vo.TokenVO;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class CoreTokenService implements TokenService {

    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;

    @Override
    public String generateToken(String email) {
        Optional<Token> optionalToken = tokenRepository.findByEmail(email)
                .map(tokenMapper::toModel);
        if(optionalToken.isEmpty()) {
            Token token = Token.of(email);
            TokenVO savedTokenVO = tokenRepository.save(tokenMapper.toVO(token));
            return savedTokenVO.token();
        } else {
            Token token = optionalToken.get();
            if (token.isExpired()) {
                token.refresh();
                tokenRepository.save(tokenMapper.toVO(token));
            }
            return token.getToken();
        }
    }

    @Override
    public Optional<String> getEmailByToken(String token) {
        return tokenRepository.findByToken(token)
                .map(tokenMapper::toModel)
                .map(target -> target.isExpired() ? null : target.getEmail());
    }
}
