package com.app.norutin.controller

import com.app.norutin.model.Desk
import com.app.norutin.model.User
import com.app.norutin.model.request.GetDesksRequest
import com.app.norutin.security.SecurityService
import com.app.norutin.service.api.DeskService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URISyntaxException

@Slf4j
@RestController
@RequestMapping("/api/desks")
@CrossOrigin
class DeskController(
    private val securityService: SecurityService,
    private val deskService: DeskService) {

    @GetMapping
    @Throws(URISyntaxException::class)
    fun getDesks(getDesksRequest: GetDesksRequest): ResponseEntity<List<Desk>> {
        val user = securityService.currentUser() ?: return ResponseEntity.badRequest().body(null)

        val desks = deskService.getUserDesks(user)

        return ResponseEntity.ok(desks)
    }
}