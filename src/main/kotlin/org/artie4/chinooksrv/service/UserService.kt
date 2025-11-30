package org.artie4.chinooksrv.service

import org.artie4.chinooksrv.entity.User

interface UserService {
    fun createUser(
        username: String,
        password: String,
        roles: Set<String>,
    ): User

    fun findUserByUsername(userName: String): User?
}
