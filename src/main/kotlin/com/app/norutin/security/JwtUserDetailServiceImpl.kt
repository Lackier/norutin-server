package com.app.norutin.security

import com.app.norutin.security.jwt.JwtUserFactory
import com.app.norutin.service.api.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class JwtUserDetailServiceImpl(
        private val userService: UserService
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.findByUserName(username)
                ?: throw UsernameNotFoundException("User with username: $username not found")
        return JwtUserFactory.create(user)
    }
}