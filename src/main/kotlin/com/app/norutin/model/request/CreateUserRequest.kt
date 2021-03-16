package com.app.norutin.model.request

data class CreateUserRequest(val email: String,
                             val name: String,
                             val password: String,
                             val phoneNumber: String)
