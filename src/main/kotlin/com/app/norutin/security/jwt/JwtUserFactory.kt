package com.app.norutin.security.jwt

import com.app.norutin.entity.user.RoleEntity
import com.app.norutin.entity.user.UserEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

class JwtUserFactory {
    companion object {
        fun create(user: UserEntity): JwtUser {
            val roles = user.roles ?: emptyList()
            return JwtUser(
                user.username,
                user.password,
                user.email,
                true,
                mapToGrantedAuthorities(roles)
            )
        }

        private fun mapToGrantedAuthorities(userRoles: List<RoleEntity?>): List<SimpleGrantedAuthority> {
            return userRoles.stream()
                .filter { it != null }
                .map {
                    SimpleGrantedAuthority(it!!.name)
                }.collect(Collectors.toList())
        }
    }
}