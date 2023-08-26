package com.app.norutin.model

data class User(val username: String,
                val password: String,
                val phone: String,
                val email: String,
                val roles: List<Role>,

                val id: Int? = null)