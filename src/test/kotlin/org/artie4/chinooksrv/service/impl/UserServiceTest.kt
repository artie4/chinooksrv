package org.artie4.chinooksrv.service.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.artie4.chinooksrv.entity.User
import org.artie4.chinooksrv.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.test.assertEquals

class UserServiceTest {
    private val userRepository: UserRepository = mockk()
    private val passwordEncoder: PasswordEncoder = mockk()
    private val customerRepository: CustomerRepository = mockk()

    private lateinit var underTest: UserService

    @BeforeEach
    fun setUp() {
        underTest = UserService(userRepository, passwordEncoder, customerRepository)
    }

    @Test
    fun createUser() {
        val username = "username"
        val password = "qwerty"
        val roles = setOf("user")
        val encodedPass = "encoded"

        val userSlot = slot<User>()

        every { passwordEncoder.encode(password) } returns encodedPass
        every { customerRepository.save(any()) } returnsArgument 0
        every { userRepository.save(capture(userSlot)) } returnsArgument 0

        underTest.createUser(username, password, roles)

        with(userSlot.captured) {
            assertEquals(encodedPass, this.password)
            assertEquals(username, this.username)
            assertEquals(roles, this.roles)
        }
    }

    @Test
    fun findUserByUsername() {
        TODO("Not implemented yet")
    }
}
