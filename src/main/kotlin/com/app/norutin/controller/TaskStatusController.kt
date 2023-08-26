package com.app.norutin.controller

import com.app.norutin.model.TaskStatus
import com.app.norutin.model.request.create.CreateDeskTaskStatusRequest
import com.app.norutin.model.request.edit.EditDeskTaskStatusRequest
import com.app.norutin.model.request.get.GetDeskTaskStatusesRequest
import com.app.norutin.model.response.ApiError
import com.app.norutin.model.response.ServerResponse
import com.app.norutin.service.api.TaskStatusService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URISyntaxException

@Slf4j
@RestController
@RequestMapping("/api/taskStatus")
@CrossOrigin
class TaskStatusController(
    private val taskStatusService: TaskStatusService
) {
    @GetMapping
    @Throws(URISyntaxException::class)
    fun getForDesk(getDeskTaskStatusRequest: GetDeskTaskStatusesRequest): ResponseEntity<List<TaskStatus>> {
        val taskStatuses = taskStatusService.getForDesk(getDeskTaskStatusRequest)

        return ResponseEntity.ok(taskStatuses)
    }

    @PostMapping("/create")
    @Throws(URISyntaxException::class)
    fun create(createDeskTaskStatusRequest: CreateDeskTaskStatusRequest): ResponseEntity<ServerResponse<TaskStatus>> {
        val taskStatus = taskStatusService.create(createDeskTaskStatusRequest)

        return ResponseEntity.ok(ServerResponse(taskStatus))
    }

    @PostMapping("/edit")
    @Throws(URISyntaxException::class)
    fun edit(editDeskTaskStatusRequest: EditDeskTaskStatusRequest): ResponseEntity<ServerResponse<TaskStatus>> {
        val taskStatus = taskStatusService.edit(editDeskTaskStatusRequest)

        return ResponseEntity.ok(ServerResponse(taskStatus))
    }

    @DeleteMapping()
    @Throws(URISyntaxException::class)
    fun delete(id: Int): ResponseEntity<ServerResponse<Int>> {
        val deletedId = taskStatusService.delete(id)

        if (deletedId.isEmpty) {
            val apiError = ApiError(HttpStatus.NOT_FOUND, "No task status found with id: $id")
            return ResponseEntity.ok(ServerResponse(apiError))
        }

        return ResponseEntity.ok(ServerResponse(deletedId.get()))
    }
}