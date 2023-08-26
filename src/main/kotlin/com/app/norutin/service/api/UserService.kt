package com.app.norutin.service.api

import com.app.norutin.model.User
import com.app.norutin.model.request.SignUpRequest

interface UserService {
    fun currentUser(): User?
    fun getCurrentUserId(): Int
    fun findByUserName(username: String): User?
    fun register(signUpRequest: SignUpRequest): User
}