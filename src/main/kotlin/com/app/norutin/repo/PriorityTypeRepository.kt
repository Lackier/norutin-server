package com.app.norutin.repo

import com.app.norutin.entity.PriorityTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PriorityTypeRepository : JpaRepository<PriorityTypeEntity, Int> {
    fun getByDeskValueId(deskValueId: Int): List<PriorityTypeEntity>
}