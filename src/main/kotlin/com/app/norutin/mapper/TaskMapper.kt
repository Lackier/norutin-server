package com.app.norutin.mapper

import com.app.norutin.entity.TaskEntity
import com.app.norutin.model.Task
import com.app.norutin.model.request.create.CreateTaskRequest
import com.app.norutin.model.response.TaskWithNames
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface TaskMapper {
    fun map(taskEntity: TaskEntity): Task
    fun map(taskEntity: CreateTaskRequest): Task
    fun map(newTask: Task): TaskEntity

    @Mappings(
        Mapping(target = "statusId", source = "status.id"),
        Mapping(target = "status", source = "status.name"),
        Mapping(target = "priorityId", source = "priority.id"),
        Mapping(target = "priority", source = "priority.name"),
        Mapping(target = "typeId", source = "type.id"),
        Mapping(target = "type", source = "type.name")
    )
    fun mapWithNames(taskEntity: TaskEntity): TaskWithNames
}