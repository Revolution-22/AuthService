package com.revolution.AuthService.domain

import com.revolution.AuthService.api.AuthService
import com.revolution.AuthService.api.exception.AuthorizationException
import com.revolution.AuthService.api.exception.UserAlreadyExistsException
import com.revolution.AuthService.api.port.Encoder
import com.revolution.AuthService.api.port.TokenService
import com.revolution.AuthService.api.port.UserRepository
import com.revolution.AuthService.api.response.UserResponse
import com.revolution.AuthService.api.vo.UserVO
import spock.lang.Specification
import spock.lang.Subject

class CoreAuthServiceSpec extends Specification implements Constants {

    private UserRepository userRepository = Mock()
    private Encoder encoder = Mock()
    private TokenService tokenService = Mock()

    private AuthBeanConfiguration authBeanConfiguration = new AuthBeanConfiguration()

    @Subject
    private AuthService authService = authBeanConfiguration.getAuthService(userRepository, encoder, tokenService)

    def "Should throw authorization exception while logging in" () {
        given:
            userRepository.findByEmail(_) >> Optional.of(getDefaultUserVO())
            encoder.matches(_,_) >> false
        when:
            authService.login(EMAIL, PASSWORD)
        then:
            thrown(AuthorizationException)
    }

    private UserVO getDefaultUserVO() {
        new UserVO(1L, NICKNAME, EMAIL, PASSWORD, Set.of(ROLE_USER))
    }

    def "Should login user" () {
        given:
            userRepository.findByEmail(_) >> Optional.of(getDefaultUserVO())
            encoder.matches(_,_) >> true
            tokenService.generateToken(_) >> TOKEN
        when:
            UserResponse response = authService.login(EMAIL, PASSWORD)
        then:
            response.nickname() == NICKNAME
            response.roles() == Set.of(ROLE_USER)
            response.token() == TOKEN
    }

    def "Should throw while registering user" () {
        given:
            userRepository.existsByEmail(_) >> true
        when:
            authService.register(NICKNAME, EMAIL, PASSWORD)
        then:
            thrown(UserAlreadyExistsException)
    }

    def "Should register user" () {
        given:
            userRepository.existsByEmail(_) >> false
            userRepository.existsByNickname(_) >> false
            encoder.encode(_) >> ENCODE_PREFIX + PASSWORD
            tokenService.generateToken(_) >> TOKEN
        when:
            UserResponse response = authService.register(NICKNAME, EMAIL, PASSWORD)
        then:
            response.nickname() == NICKNAME
            response.roles() == Set.of(ROLE_USER)
            response.token() == TOKEN
    }

    def "Should throw while validate token because of null token" () {
        given:
            tokenService.getEmailByToken(_) >> Optional.empty()
        when:
            authService.validateToken(TOKEN)
        then:
            thrown(AuthorizationException)
    }

    def "Should throw while validate token because of null user" () {
        given:
            tokenService.getEmailByToken(_) >> Optional.of(EMAIL)
            userRepository.findByEmail(_) >> Optional.empty()
        when:
            authService.validateToken(TOKEN)
        then:
            thrown(AuthorizationException)
    }

    def "Should validate token" () {
        given:
            tokenService.getEmailByToken(_) >> Optional.of(EMAIL)
            userRepository.findByEmail(_) >> Optional.of(getDefaultUserVO())
            tokenService.generateToken(_) >> TOKEN
        when:
            UserResponse response = authService.validateToken(TOKEN)
        then:
            response.nickname() == NICKNAME
            response.roles() == Set.of(ROLE_USER)
            response.token() == TOKEN
    }
}
