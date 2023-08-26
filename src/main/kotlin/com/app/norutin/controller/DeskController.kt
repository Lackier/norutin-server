package com.app.norutin.controller

import com.app.norutin.model.Desk
import com.app.norutin.model.request.create.CreateDeskRequest
import com.app.norutin.model.request.edit.EditDeskRequest
import com.app.norutin.model.request.get.GetDesksRequest
import com.app.norutin.model.response.ApiError
import com.app.norutin.model.response.ServerResponse
import com.app.norutin.service.api.DeskService
import com.app.norutin.service.api.UserService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URISyntaxException

@Slf4j
@RestController
@RequestMapping("/api/desks")
@CrossOrigin
class DeskController(
    private val userService: UserService,
    private val deskService: DeskService
) {

    @GetMapping
    @Throws(URISyntaxException::class)
    fun getDesks(getDesksRequest: GetDesksRequest): ResponseEntity<List<Desk>> {
        val user = userService.currentUser() ?: return ResponseEntity.badRequest().body(null)

        val desks = deskService.getUserDesks(user)

        return ResponseEntity.ok(desks)
    }

    @GetMapping("/get")
    @Throws(URISyntaxException::class)
    fun getDesk(deskId: Int): ResponseEntity<ServerResponse<Desk>> {
        val desk = deskService.getDesk(deskId)

        if (desk.isEmpty) {
            val apiError = ApiError(HttpStatus.NOT_FOUND, "No desk found with id: $deskId")
            return ResponseEntity.ok(ServerResponse(apiError))
        }

        return ResponseEntity.ok(ServerResponse(desk.get()))
    }

    @PostMapping("/create")
    @Throws(URISyntaxException::class)
    fun createDesk(createDeskRequest: CreateDeskRequest): ResponseEntity<ServerResponse<Desk>> {
        val user = userService.currentUser() ?: return ResponseEntity.badRequest().body(null)

        val desk = deskService.create(createDeskRequest, user.id!!)

        return ResponseEntity.ok(ServerResponse(desk))
    }

    @PostMapping("/edit")
    @Throws(URISyntaxException::class)
    fun editDesk(editDeskRequest: EditDeskRequest): ResponseEntity<ServerResponse<Desk>> {
        val user = userService.currentUser() ?: return ResponseEntity.badRequest().body(null)

        val desk = deskService.edit(editDeskRequest, user.id!!)

        return ResponseEntity.ok(ServerResponse(desk))
    }

    @DeleteMapping()
    @Throws(URISyntaxException::class)
    fun deleteDesk(deskId: Int): ResponseEntity<ServerResponse<Int>> {
        val deletedDeskId = deskService.delete(deskId)

        if (deletedDeskId.isEmpty) {
            val apiError = ApiError(HttpStatus.NOT_FOUND, "No desk found with id: $deskId")
            return ResponseEntity.ok(ServerResponse(apiError))
        }

        return ResponseEntity.ok(ServerResponse(deletedDeskId.get()))
    }
}