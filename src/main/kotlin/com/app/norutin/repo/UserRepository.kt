package com.app.norutin.repo

import com.app.norutin.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findByUsername(uid: String): UserEntity?
    fun save(user: UserEntity): UserEntity
}