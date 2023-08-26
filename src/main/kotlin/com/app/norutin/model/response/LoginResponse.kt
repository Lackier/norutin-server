package com.app.norutin.model.response

import com.app.norutin.model.User

data class LoginResponse(
        val user: User,
        val token: String
)