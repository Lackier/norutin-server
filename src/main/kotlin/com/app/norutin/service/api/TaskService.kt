package com.app.norutin.service.api

import com.app.norutin.model.Task
import com.app.norutin.model.request.create.CreateTaskRequest
import com.app.norutin.model.request.edit.EditTaskRequest
import com.app.norutin.model.response.TaskWithNames
import java.util.*

interface TaskService {
    fun getDeskTasks(deskId: Int): List<Task>
    fun getTask(id: Int): Optional<Task>
    fun create(createTaskRequest: CreateTaskRequest): Task
    fun edit(editTaskRequest: EditTaskRequest): Optional<Task>
    fun delete(id: Int): Optional<Int>
    fun getDeskTasksWithNames(deskId: Int): List<TaskWithNames>
}