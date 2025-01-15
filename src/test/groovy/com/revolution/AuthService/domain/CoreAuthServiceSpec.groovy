package com.revolution.AuthService.domain

import com.revolution.AuthService.api.AuthService
import com.revolution.AuthService.api.exception.AuthorizationException
import com.revolution.AuthService.api.response.UserResponse
import com.revolution.AuthService.domain.adapters.TestBeanConfiguration
import spock.lang.Specification
import spock.lang.Subject

class CoreAuthServiceSpec extends Specification implements Constants {

    private TestBeanConfiguration configuration = new TestBeanConfiguration()

    @Subject
    private AuthService authService = configuration.getAuthService()

    def setup(){
       configuration.clear()
    }

    def "should not login user because he didn't exists in database" () {
        when: "Try to login user"
            authService.login(EMAIL, PASSWORD)
        then: "Exception throwed because user not found in database"
            thrown(AuthorizationException)
    }

    def "should register new user and allow to login" () {
        when: "Try to register new user"
            authService.register(NICKNAME, EMAIL, PASSWORD)
        and: "Try to login as created user"
            UserResponse userResponse = authService.login(EMAIL, PASSWORD)
        then: "Check if logged user is registered user"
            userResponse.nickname() == NICKNAME
    }

    def "should not login user because he's password not match" () {
        given: "Register user in application"
            authService.register(NICKNAME, EMAIL, PASSWORD)
        when: "Try to login with wrong password"
            authService.login(EMAIL, EMAIL)
        then: "Exception throwed because users password not match"
            thrown(AuthorizationException)
    }

    def "should not validate token because user not logged before" () {
        when:
            authService.validateToken(TOKEN)
        then:
            thrown(AuthorizationException)
    }

    def "should validate token because user is logged before" () {
        given: "Register new user in application and get token"
            UserResponse userResponse = authService.register(NICKNAME, EMAIL, PASSWORD)
        when: "Validate registered token"
            UserResponse tokenResponse = authService.validateToken(userResponse.token())
        then: "Check if validated user is registered user"
            tokenResponse.nickname() == userResponse.nickname()
    }

    def "asdasdas" () {
        when:
            def x = 0
        then:
            x == 1
    }
}
