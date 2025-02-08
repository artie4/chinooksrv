package org.artie4.chinooksrv.controller

import org.artie4.chinooksrv.util.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
) {
    @PostMapping("/authenticate")
    fun createAuthenticationToken(
        @RequestBody authenticationRequest: AuthenticationRequest,
    ): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password,
            ),
        )

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val jwt = JwtUtil.generateToken(userDetails)

        return AuthenticationResponse(jwt)
    }
}

data class AuthenticationRequest(val username: String, val password: String)

data class AuthenticationResponse(val jwt: String)
