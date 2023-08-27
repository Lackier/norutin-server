package com.app.norutin.repo

import com.app.norutin.entity.desk.DeskEntity
import com.app.norutin.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DeskRepository : JpaRepository<DeskEntity, Int> {
    override fun findById(id: Int): Optional<DeskEntity>
    fun findByUser(user: UserEntity): List<DeskEntity>
    fun findByUserId(userId: Int): List<DeskEntity>
}
