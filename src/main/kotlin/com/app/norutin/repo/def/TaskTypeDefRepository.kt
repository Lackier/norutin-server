package com.app.norutin.repo.def

import com.app.norutin.entity.def.TaskTypeDefEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskTypeDefRepository : JpaRepository<TaskTypeDefEntity, Int> {
}