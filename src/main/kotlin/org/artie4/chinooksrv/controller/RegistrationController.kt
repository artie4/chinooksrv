package org.artie4.chinooksrv.controller

import org.artie4.chinooksrv.service.impl.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationController(
    private val userService: UserService,
) {
    @PostMapping("/register")
    fun registerUser(
        @RequestBody registrationRequest: RegistrationRequest,
    ): String {
        userService.createUser(
            username = registrationRequest.username,
            password = registrationRequest.password,
            roles = setOf("USER"), // Роли по умолчанию
        )
        return "User registered successfully"
    }
}

data class RegistrationRequest(val username: String, val password: String)
