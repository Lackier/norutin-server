package com.app.norutin.repo

import com.app.norutin.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeskRepository : JpaRepository<UserEntity, Int> {

}
