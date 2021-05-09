package com.app.norutin.mapper

import com.app.norutin.entity.DeskEntity
import com.app.norutin.model.Desk
import com.app.norutin.model.request.CreateDesksRequest
import org.mapstruct.Mapper

@Mapper
interface DeskMapper {
    fun map(entity: DeskEntity): Desk
    fun map(account: Desk): DeskEntity
    fun map(createDeskRequest: CreateDesksRequest): Desk
}
