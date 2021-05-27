package com.app.norutin.service.api

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.PriorityTypeEntity

interface PriorityTypeService {
    fun createDefault(deskValueEntity: DeskValueEntity): MutableList<PriorityTypeEntity>
}