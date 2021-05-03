package com.app.norutin.service.api

import com.app.norutin.model.Desk
import com.app.norutin.model.User
import java.util.*

interface DeskService {
    fun getUserDesks(user: User): List<Desk>
    fun getDesk(id: Int): Optional<Desk>
}