package com.app.norutin.service.api

import com.app.norutin.model.Desk
import com.app.norutin.model.User
import com.app.norutin.model.request.CreateDesksRequest
import java.util.*

interface DeskService {
    fun getUserDesks(user: User): List<Desk>
    fun getDesk(id: Int): Optional<Desk>
    fun create(createDeskRequest: CreateDesksRequest, userId: Int): Desk
}