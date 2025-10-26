package org.artie4.chinooksrv.service.impl

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.artie4.chinooksrv.entity.User
import org.artie4.chinooksrv.repository.CustomerRepository
import org.artie4.chinooksrv.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.password.PasswordEncoder

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

        assertSoftly {
            with(userSlot.captured) {
                encodedPass shouldBe this.password
                username shouldBe this.username
                roles shouldBe this.roles
            }
        }
    }

    @Test
    fun findUserByUsername() {
        val usernameValue = "username"

        val user =
            mockk<User> {
                every { username } returns usernameValue
            }

        every { userRepository.findByUsername(usernameValue) } returns user

        val result = underTest.findUserByUsername(usernameValue)

        result?.username shouldBe usernameValue
    }
}
