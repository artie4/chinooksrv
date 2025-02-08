package org.artie4.chinooksrv.service.impl

import org.artie4.chinooksrv.entity.User
import org.artie4.chinooksrv.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun createUser(
        username: String,
        password: String,
        roles: Set<String>,
    ): User {
        val user =
            User(
                username = username,
                password = passwordEncoder.encode(password),
                roles = roles,
            )
        return userRepository.save(user)
    }
}
