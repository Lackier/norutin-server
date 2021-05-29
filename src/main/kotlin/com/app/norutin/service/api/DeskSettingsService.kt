package com.app.norutin.service.api

import com.app.norutin.entity.DeskEntity
import com.app.norutin.entity.DeskValueEntity

interface DeskSettingsService {
    fun createDefaultSettings(deskId: Int)
    fun deleteAll(deskId: Int)
    fun createSettings(desk: DeskEntity): DeskValueEntity
}