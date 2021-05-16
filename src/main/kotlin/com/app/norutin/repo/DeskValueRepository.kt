package com.app.norutin.repo

import com.app.norutin.entity.DeskValueEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeskValueRepository : JpaRepository<DeskValueEntity, Int> {
}