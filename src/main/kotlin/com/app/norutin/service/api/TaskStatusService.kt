package com.app.norutin.service.api

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.TaskStatusEntity

interface TaskStatusService {
    fun createDefault(deskValueEntity: DeskValueEntity): MutableList<TaskStatusEntity>
}