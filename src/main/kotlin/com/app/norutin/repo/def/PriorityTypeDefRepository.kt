package com.app.norutin.repo.def

import com.app.norutin.entity.def.PriorityTypeDefEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PriorityTypeDefRepository : JpaRepository<PriorityTypeDefEntity, Int> {
}