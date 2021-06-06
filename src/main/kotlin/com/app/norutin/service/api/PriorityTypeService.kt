package com.app.norutin.service.api

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.PriorityTypeEntity
import com.app.norutin.model.TaskPriority
import com.app.norutin.model.request.create.CreateDeskPriorityTypeRequest
import com.app.norutin.model.request.edit.EditDeskPriorityTypeRequest
import com.app.norutin.model.request.get.GetDeskPriorityTypesRequest
import java.util.*

interface PriorityTypeService {
    fun createDefault(deskValueEntity: DeskValueEntity): MutableList<PriorityTypeEntity>
    fun getForDesk(getDeskPriorityTypesRequest: GetDeskPriorityTypesRequest): List<TaskPriority>
    fun create(createDeskPriorityTypeRequest: CreateDeskPriorityTypeRequest): TaskPriority
    fun edit(editDeskPriorityTypeRequest: EditDeskPriorityTypeRequest): TaskPriority
    fun delete(id: Int): Optional<Int>
    fun deleteAll(deskValueId: Int)
    fun find(priorityId: Int): PriorityTypeEntity
}