package com.app.norutin.service.api

import com.app.norutin.model.Desk
import com.app.norutin.model.User
import com.app.norutin.model.request.CreateDeskRequest
import com.app.norutin.model.request.EditDeskRequest
import java.util.*

interface DeskService {
    fun getUserDesks(user: User): List<Desk>
    fun getDesk(id: Int): Optional<Desk>
    fun create(createDeskRequest: CreateDeskRequest, userId: Int): Desk
    fun edit(editDeskRequest: EditDeskRequest, userId: Int): Desk
}