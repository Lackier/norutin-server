package com.app.norutin.service.api

import com.app.norutin.model.Task

interface TaskService {
    fun getDeskTasks(deskId: Int): List<Task>
}