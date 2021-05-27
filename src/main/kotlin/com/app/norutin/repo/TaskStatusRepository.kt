package com.app.norutin.repo

import com.app.norutin.entity.TaskStatusEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskStatusRepository : JpaRepository<TaskStatusEntity, Int> {
    fun getByDeskValueId(deskValueId: Int): List<TaskStatusEntity>
}