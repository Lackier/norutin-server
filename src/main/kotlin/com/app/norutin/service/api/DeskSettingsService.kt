package com.app.norutin.service.api

import com.app.norutin.entity.desk.DeskEntity
import com.app.norutin.entity.settings.DeskValueEntity

interface DeskSettingsService {
    fun createDefaultSettings(deskId: Int)
    fun deleteAll(deskId: Int)
    fun createSettings(desk: DeskEntity): DeskValueEntity
}