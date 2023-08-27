package com.app.norutin.security

import com.app.norutin.repo.UserRepository
import com.app.norutin.security.jwt.JwtUserFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class JwtUserDetailServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("Not found user with such username: $username")
        return JwtUserFactory.create(user)
    }
}