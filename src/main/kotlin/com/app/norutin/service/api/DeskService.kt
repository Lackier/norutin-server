package com.app.norutin.service.api

import com.app.norutin.model.Desk
import com.app.norutin.model.User

interface DeskService {
    fun getUserDesks(user: User): List<Desk>
}