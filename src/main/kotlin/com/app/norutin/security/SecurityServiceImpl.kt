package com.app.norutin.security

import com.app.norutin.exception.AuthFailedException
import com.app.norutin.model.User
import com.app.norutin.service.impl.UserServiceImpl
import com.google.firebase.auth.FirebaseToken
import lombok.extern.slf4j.Slf4j
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Slf4j
@Service
class SecurityServiceImpl : SecurityService {
    private val userService: UserServiceImpl? = null

    override fun authenticatedUserUid(): String {
        val token = SecurityContextHolder.getContext()
            .authentication
            .principal as FirebaseToken
        return token.uid
    }

    override fun currentUser(): User {
        val uid = authenticatedUserUid()
        return userService?.findByUid(uid) ?: throw AuthFailedException()
    }
}