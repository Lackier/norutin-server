package com.app.norutin.controller

import com.app.norutin.model.Task
import com.app.norutin.model.request.get.GetTasksRequest
import com.app.norutin.service.api.TaskService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
}