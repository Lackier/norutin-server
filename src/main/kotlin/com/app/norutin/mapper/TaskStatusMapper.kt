package com.app.norutin.mapper

import com.app.norutin.entity.TaskStatusEntity
import com.app.norutin.entity.def.TaskStatusDefEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface TaskStatusMapper {
    @Mapping(target = "id", ignore = true)
    fun map(defEntity: TaskStatusDefEntity): TaskStatusEntity
}