package com.app.norutin.service.api

import com.app.norutin.model.Task
import com.app.norutin.model.request.create.CreateTaskRequest
import com.app.norutin.model.request.edit.EditTaskRequest
import java.util.*

interface TaskService {
    fun getDeskTasks(deskId: Int): List<Task>
    fun getTask(taskId: Int): Optional<Task>
    fun create(createTaskRequest: CreateTaskRequest): Task
    fun edit(editTaskRequest: EditTaskRequest): Optional<Task>
    fun delete(taskId: Int): Optional<Int>
    fun getDeskTasksByStatus(deskId: Int, statusId: Int): List<Task>
}