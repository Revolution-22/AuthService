package com.revolution.auth.service.domain

import com.revolution.auth.service.api.port.AuthService
import com.revolution.auth.service.api.exception.AuthorizationException
import com.revolution.auth.service.api.response.UserResponse
import com.revolution.auth.service.domain.adapters.TestBeanConfiguration
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
        then: "Exception threw because user not found in database"
            thrown(AuthorizationException)
    }

    def "should register new user and allow to login" () {
        when: "Try to register new user"
            authService.register(NICKNAME, EMAIL, PASSWORD)
        and: "Try to login as created user"
            UserResponse userResponse = authService.login(EMAIL, PASSWORD)
        then: "Check if logged user is registered user"
            userResponse.email() == EMAIL
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
        when: "Validate not logged token"
            authService.validateToken(TOKEN)
        then: "Check if threw authorization error"
            thrown(AuthorizationException)
    }

    def "should validate token because user is logged before" () {
        given: "Register new user in application and get token"
            UserResponse userResponse = authService.register(NICKNAME, EMAIL, PASSWORD)
        when: "Validate registered token"
            UserResponse tokenResponse = authService.validateToken(userResponse.token())
        then: "Check if validated user is registered user"
            tokenResponse.email() == userResponse.email()
    }

    def "should not refresh token because user not logged" () {
        when: "Try to refresh token"
            authService.refreshToken(TOKEN)
        then: "Check if threw authorization exception"
            thrown(AuthorizationException)
    }

    def "should refresh token" () {
        given: "Register user in application"
            UserResponse registerResponse = authService.register(NICKNAME, EMAIL, PASSWORD)
        when: "Try to refresh token"
            UserResponse refreshResponse = authService.refreshToken(registerResponse.refreshToken())
        then: "Check if access token and refresh token was changed"
            registerResponse.refreshToken() != refreshResponse.refreshToken()
            registerResponse.token() != refreshResponse.token()
    }

    def "should not refresh token because token not found in database" () {
        given: "Register user in application"
            authService.register(NICKNAME, EMAIL, PASSWORD)
        when: "Try to refresh token"
            authService.refreshToken(TOKEN)
        then: "Check if threw authorization exception"
            thrown(AuthorizationException)
    }

}
