package com.app.norutin.security

import com.app.norutin.model.User

interface SecurityService {
    /**
     * Get authenticated account uid from SecurityContextHolder
     *
     * @return authenticated account uid
     */
    fun authenticatedUserUid(): String?

    /**
     * Retrieving account from database with current authenticated user uid
     *
     * @return account of authenticated user
     */
    fun currentUser(): User?
}