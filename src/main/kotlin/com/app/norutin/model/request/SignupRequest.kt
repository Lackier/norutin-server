package com.app.norutin.model.request

data class SignUpRequest(
        val username: String,
        val password: String,
        val phone: String,
        val email: String,
)