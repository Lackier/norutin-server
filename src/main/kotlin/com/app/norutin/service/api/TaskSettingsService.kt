package com.app.norutin.service.api

import com.app.norutin.entity.DeskEntity
import com.app.norutin.entity.DeskValueEntity

interface TaskSettingsService {
    fun createDefaultSettings(desk: DeskEntity): DeskValueEntity
}