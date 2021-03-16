package com.app.norutin.service.api

import com.app.norutin.model.User

interface UserService {
    fun findByUid(oid: String): User?

    fun save(user: User): User
}