package com.app.norutin.mapper

import com.app.norutin.entity.settings.DeskValueEntity
import com.app.norutin.entity.settings.TaskTypeEntity
import com.app.norutin.entity.def.TaskTypeDefEntity
import com.app.norutin.model.TaskType
import com.app.norutin.model.request.create.CreateDeskTaskTypeRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface TaskTypeMapper {
    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "deskValue", source = "deskValueEntity")
    )
    fun map(defEntity: TaskTypeDefEntity, deskValueEntity: DeskValueEntity): TaskTypeEntity

    fun map(taskTypeEntity: TaskTypeEntity): TaskType

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "deskValue", source = "deskValueEntity")
    )
    fun map(
        createDeskTaskTypeRequest: CreateDeskTaskTypeRequest,
        deskValueEntity: DeskValueEntity
    ): TaskTypeEntity
}