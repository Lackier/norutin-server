package com.app.norutin.mapper

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.TaskTypeEntity
import com.app.norutin.entity.def.TaskTypeDefEntity
import com.app.norutin.model.TaskType
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface TaskTypeMapper {
    @Mapping(target = "id", ignore = true)
    fun map(defEntity: TaskTypeDefEntity, deskValueEntity: DeskValueEntity): TaskTypeEntity
    fun map(taskTypeEntity: TaskTypeEntity): TaskType
}