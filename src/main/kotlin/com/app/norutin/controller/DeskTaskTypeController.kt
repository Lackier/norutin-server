package com.app.norutin.controller

import com.app.norutin.model.TaskType
import com.app.norutin.model.request.*
import com.app.norutin.model.response.ApiError
import com.app.norutin.model.response.ServerResponse
import com.app.norutin.service.api.TaskTypeService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URISyntaxException

@Slf4j
@RestController
@RequestMapping("/api/deskTaskType")
@CrossOrigin
class DeskTaskTypeController(
    private val taskTypeService: TaskTypeService
) {
    @GetMapping
    @Throws(URISyntaxException::class)
    fun getForDesk(getDeskTaskTypesRequest: GetDeskTaskTypesRequest): ResponseEntity<List<TaskType>> {
        val taskTypes = taskTypeService.getForDesk(getDeskTaskTypesRequest)

        return ResponseEntity.ok(taskTypes)
    }

    @PostMapping("/create")
    @Throws(URISyntaxException::class)
    fun create(createDeskTaskTypeRequest: CreateDeskTaskTypeRequest): ResponseEntity<ServerResponse<TaskType>> {
        val taskType = taskTypeService.create(createDeskTaskTypeRequest)

        return ResponseEntity.ok(ServerResponse(taskType))
    }

    @PostMapping("/edit")
    @Throws(URISyntaxException::class)
    fun edit(editDeskTaskTypeRequest: EditDeskTaskTypeRequest): ResponseEntity<ServerResponse<TaskType>> {
        val taskType = taskTypeService.edit(editDeskTaskTypeRequest)

        return ResponseEntity.ok(ServerResponse(taskType))
    }

    @DeleteMapping()
    @Throws(URISyntaxException::class)
    fun delete(id: Int): ResponseEntity<ServerResponse<Int>> {
        val deletedId = taskTypeService.delete(id)

        if (deletedId.isEmpty) {
            val apiError = ApiError(HttpStatus.NOT_FOUND, "No priority type found with id: $id")
            return ResponseEntity.ok(ServerResponse(apiError))
        }

        return ResponseEntity.ok(ServerResponse(deletedId.get()))
    }
}