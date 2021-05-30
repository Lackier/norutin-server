package com.app.norutin.controller

import com.app.norutin.model.TaskPriority
import com.app.norutin.model.request.create.CreateDeskPriorityTypeRequest
import com.app.norutin.model.request.edit.EditDeskPriorityTypeRequest
import com.app.norutin.model.request.get.GetDeskPriorityTypesRequest
import com.app.norutin.model.response.ApiError
import com.app.norutin.model.response.ServerResponse
import com.app.norutin.service.api.PriorityTypeService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URISyntaxException

@Slf4j
@RestController
@RequestMapping("/api/deskPriorityType")
@CrossOrigin
class DeskPriorityTypesController(
    private val priorityTypeService: PriorityTypeService
) {
    @GetMapping
    @Throws(URISyntaxException::class)
    fun getForDesk(getDeskPriorityTypesRequest: GetDeskPriorityTypesRequest): ResponseEntity<List<TaskPriority>> {
        val priorityTypes = priorityTypeService.getForDesk(getDeskPriorityTypesRequest)

        return ResponseEntity.ok(priorityTypes)
    }

    @PostMapping("/create")
    @Throws(URISyntaxException::class)
    fun create(createDeskPriorityTypeRequest: CreateDeskPriorityTypeRequest): ResponseEntity<ServerResponse<TaskPriority>> {
        val priorityType = priorityTypeService.create(createDeskPriorityTypeRequest)

        return ResponseEntity.ok(ServerResponse(priorityType))
    }

    @PostMapping("/edit")
    @Throws(URISyntaxException::class)
    fun edit(editDeskPriorityTypeRequest: EditDeskPriorityTypeRequest): ResponseEntity<ServerResponse<TaskPriority>> {
        val priorityType = priorityTypeService.edit(editDeskPriorityTypeRequest)

        return ResponseEntity.ok(ServerResponse(priorityType))
    }

    @DeleteMapping()
    @Throws(URISyntaxException::class)
    fun delete(id: Int): ResponseEntity<ServerResponse<Int>> {
        val deletedId = priorityTypeService.delete(id)

        if (deletedId.isEmpty) {
            val apiError = ApiError(HttpStatus.NOT_FOUND, "No priority type found with id: $id")
            return ResponseEntity.ok(ServerResponse(apiError))
        }

        return ResponseEntity.ok(ServerResponse(deletedId.get()))
    }
}