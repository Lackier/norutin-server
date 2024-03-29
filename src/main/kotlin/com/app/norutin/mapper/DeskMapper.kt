package com.app.norutin.mapper

import com.app.norutin.entity.desk.DeskEntity
import com.app.norutin.model.Desk
import com.app.norutin.model.request.create.CreateDeskRequest
import com.app.norutin.model.request.edit.EditDeskRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface DeskMapper {
    @Mappings(Mapping(target="userId", source="user.id"))
    fun map(entity: DeskEntity): Desk

    fun map(desk: Desk): DeskEntity

    fun map(createDeskRequest: CreateDeskRequest): Desk

    fun map(editDeskRequest: EditDeskRequest): Desk
}
