package com.app.norutin.repo

import com.app.norutin.entity.settings.TaskTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskTypeRepository : JpaRepository<TaskTypeEntity, Int> {
    fun getByDeskValueId(deskValueId: Int): List<TaskTypeEntity>
}