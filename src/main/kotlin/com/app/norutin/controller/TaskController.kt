package com.app.norutin.controller

import com.app.norutin.model.Desk
import com.app.norutin.model.Task
import com.app.norutin.model.request.create.CreateDeskRequest
import com.app.norutin.model.request.create.CreateTaskRequest
import com.app.norutin.model.request.edit.EditDeskRequest
import com.app.norutin.model.request.edit.EditTaskRequest
import com.app.norutin.model.request.get.GetTasksRequest
import com.app.norutin.model.response.ApiError
import com.app.norutin.model.response.ServerResponse
import com.app.norutin.service.api.TaskService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URISyntaxException

@Slf4j
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin

class TaskController(
    private val taskService: TaskService
) {

    @GetMapping
    @Throws(URISyntaxException::class)
    fun getTasks(getTasksRequest: GetTasksRequest): ResponseEntity<List<Task>> {
        val tasks = taskService.getDeskTasks(getTasksRequest.deskId)

        return ResponseEntity.ok(tasks)
    }

    @GetMapping("/get")
    @Throws(URISyntaxException::class)
    fun getTask(taskId: Int): ResponseEntity<ServerResponse<Task>> {
        val task = taskService.getTask(taskId)

        if (task.isEmpty) {
            val apiError = ApiError(HttpStatus.NOT_FOUND, "No task found with id: $taskId")
            return ResponseEntity.ok(ServerResponse(apiError))
        }

        return ResponseEntity.ok(ServerResponse(task.get()))
    }

    @PostMapping("/create")
    @Throws(URISyntaxException::class)
    fun createTask(createTaskRequest: CreateTaskRequest): ResponseEntity<ServerResponse<Task>> {
        val task = taskService.create(createTaskRequest)

        return ResponseEntity.ok(ServerResponse(task))
    }

    @PostMapping("/edit")
    @Throws(URISyntaxException::class)
    fun editTask(editTaskRequest: EditTaskRequest): ResponseEntity<ServerResponse<Task>> {
        val task = taskService.edit(editTaskRequest)

        if (task.isEmpty) {
            val apiError = ApiError(HttpStatus.NOT_FOUND, "No task found with id: ${editTaskRequest.id}")
            return ResponseEntity.ok(ServerResponse(apiError))
        }

        return ResponseEntity.ok(ServerResponse(task.get()))
    }

    @DeleteMapping()
    @Throws(URISyntaxException::class)
    fun deleteTask(taskId: Int): ResponseEntity<ServerResponse<Int>> {
        val deletedTaskId = taskService.delete(taskId)

        if (deletedTaskId.isEmpty) {
            val apiError = ApiError(HttpStatus.NOT_FOUND, "No task found with id: $taskId")
            return ResponseEntity.ok(ServerResponse(apiError))
        }

        return ResponseEntity.ok(ServerResponse(deletedTaskId.get()))
    }
}