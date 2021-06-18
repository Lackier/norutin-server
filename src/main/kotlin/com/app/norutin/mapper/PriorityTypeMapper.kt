package com.app.norutin.mapper

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.PriorityTypeEntity
import com.app.norutin.entity.def.PriorityTypeDefEntity
import com.app.norutin.model.TaskPriority
import com.app.norutin.model.request.create.CreateDeskPriorityTypeRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface PriorityTypeMapper {
    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "deskValue", source = "deskValueEntity")
    )
    fun map(defEntity: PriorityTypeDefEntity, deskValueEntity: DeskValueEntity): PriorityTypeEntity

    fun map(priorityTypeEntity: PriorityTypeEntity): TaskPriority

    @Mappings(
        Mapping(target = "deskValue", source = "deskValueEntity")
    )
    fun map(
        createDeskPriorityTypeRequest: CreateDeskPriorityTypeRequest,
        deskValueEntity: DeskValueEntity
    ): PriorityTypeEntity
}