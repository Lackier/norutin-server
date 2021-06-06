package com.app.norutin.mapper

import com.app.norutin.entity.TaskEntity
import com.app.norutin.model.Task
import com.app.norutin.model.request.create.CreateTaskRequest
import org.mapstruct.Mapper

@Mapper
interface TaskMapper {
    fun map(taskEntity: TaskEntity): Task
    fun map(taskEntity: CreateTaskRequest): Task
    fun map(newTask: Task): TaskEntity
}