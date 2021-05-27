package com.app.norutin.service.api

import com.app.norutin.entity.DeskEntity
import com.app.norutin.entity.DeskValueEntity

interface DeskSettingsService {
    fun createDefaultSettings(desk: DeskEntity): DeskValueEntity
    fun deleteAll(deskId: Int)
}