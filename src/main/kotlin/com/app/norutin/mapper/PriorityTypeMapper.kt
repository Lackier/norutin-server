package com.app.norutin.mapper

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.PriorityTypeEntity
import com.app.norutin.entity.def.PriorityTypeDefEntity
import com.app.norutin.model.TaskPriority
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface PriorityTypeMapper {
    @Mapping(target = "id", ignore = true)
    fun map(defEntity: PriorityTypeDefEntity, deskValueEntity: DeskValueEntity): PriorityTypeEntity
    fun map(priorityTypeEntity: PriorityTypeEntity): TaskPriority
}