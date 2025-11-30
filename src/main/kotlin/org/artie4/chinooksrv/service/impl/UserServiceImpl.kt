package org.artie4.chinooksrv.service.impl

import jakarta.transaction.Transactional
import org.artie4.chinooksrv.entity.Customer
import org.artie4.chinooksrv.entity.User
import org.artie4.chinooksrv.repository.CustomerRepository
import org.artie4.chinooksrv.repository.UserRepository
import org.artie4.chinooksrv.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val customerRepository: CustomerRepository,
) : UserService {
    @Transactional
    override fun createUser(
        username: String,
        password: String,
        roles: Set<String>,
    ): User {
        val user =
            User(
                username = username,
                password = passwordEncoder.encode(password),
                roles = roles,
                customer = customerRepository.save(Customer()),
            )
        return userRepository.save(user)
    }

    override fun findUserByUsername(userName: String): User? {
        return userRepository.findByUsername(userName)
    }
}
