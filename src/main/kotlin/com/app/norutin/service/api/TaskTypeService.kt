package com.app.norutin.service.api

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.TaskTypeEntity

interface TaskTypeService {
    fun createDefault(deskValueEntity: DeskValueEntity): MutableList<TaskTypeEntity>
}