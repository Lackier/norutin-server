package com.app.norutin.repo

import com.app.norutin.entity.user.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<RoleEntity, Int> {
    fun findByName(name: String): RoleEntity?
}