package com.revolution.AuthService.domain

import com.revolution.AuthService.api.AuthService
import com.revolution.AuthService.api.exception.AuthorizationException
import com.revolution.AuthService.api.port.Encoder
import com.revolution.AuthService.api.port.TokenService
import com.revolution.AuthService.api.port.UserRepository
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
}
