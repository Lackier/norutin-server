package com.app.norutin.repo

import com.app.norutin.entity.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<TaskEntity, Int> {
    fun findByDeskId(deskId: Int): List<TaskEntity>
}