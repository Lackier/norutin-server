package com.app.norutin.mapper

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.TaskStatusEntity
import com.app.norutin.entity.def.TaskStatusDefEntity
import com.app.norutin.model.TaskStatus
import com.app.norutin.model.request.create.CreateDeskTaskStatusRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface TaskStatusMapper {
    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "deskValue", source = "deskValueEntity")
    )
    fun map(defEntity: TaskStatusDefEntity, deskValueEntity: DeskValueEntity): TaskStatusEntity

    fun map(taskStatusEntity: TaskStatusEntity): TaskStatus

    fun map(
        createDeskTaskStatusRequest: CreateDeskTaskStatusRequest,
        deskValueEntity: DeskValueEntity
    ): TaskStatusEntity
}