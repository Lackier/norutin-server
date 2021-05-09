package com.app.norutin.controller

import com.app.norutin.model.Desk
import com.app.norutin.model.request.CreateDesksRequest
import com.app.norutin.model.request.GetDesksRequest
import com.app.norutin.model.response.ApiError
import com.app.norutin.model.response.ServerResponse
import com.app.norutin.security.SecurityService
import com.app.norutin.service.api.DeskService
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
    private val securityService: SecurityService,
    private val deskService: DeskService
) {

    @GetMapping
    @Throws(URISyntaxException::class)
    fun getDesks(getDesksRequest: GetDesksRequest): ResponseEntity<List<Desk>> {
        val user = securityService.currentUser() ?: return ResponseEntity.badRequest().body(null)

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
    fun createDesk(createDeskRequest: CreateDesksRequest): ResponseEntity<ServerResponse<Desk>> {
        val user = securityService.currentUser() ?: return ResponseEntity.badRequest().body(null)

        val desk = deskService.create(createDeskRequest, user.id!!)

        return ResponseEntity.ok(ServerResponse(desk))
    }
}