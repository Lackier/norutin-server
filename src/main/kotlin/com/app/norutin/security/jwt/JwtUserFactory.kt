package com.app.norutin.security.jwt

import com.app.norutin.model.Role
import com.app.norutin.model.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

class JwtUserFactory {
    companion object {
        fun create(user: User): JwtUser {
            return JwtUser(
                user.username,
                user.password,
                user.email,
                true,
                mapToGrantedAuthorities(user.roles)
            )
        }

        private fun mapToGrantedAuthorities(userRoles: List<Role>): List<SimpleGrantedAuthority> {
            return userRoles.stream()
                .map {
                    SimpleGrantedAuthority(it.name)
                }.collect(Collectors.toList())
        }
    }
}