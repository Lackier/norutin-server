package com.app.norutin.mapper

import com.app.norutin.entity.TaskEntity
import com.app.norutin.model.Task
import org.mapstruct.Mapper

@Mapper
interface TaskMapper {
    fun map(taskEntity: TaskEntity): Task
}