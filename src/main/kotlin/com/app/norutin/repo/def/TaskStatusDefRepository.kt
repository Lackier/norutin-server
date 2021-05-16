package com.app.norutin.repo.def

import com.app.norutin.entity.def.TaskStatusDefEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskStatusDefRepository : JpaRepository<TaskStatusDefEntity, Int> {
}