package com.app.norutin.service.api

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.TaskTypeEntity
import com.app.norutin.model.TaskType
import com.app.norutin.model.request.CreateDeskTaskTypeRequest
import com.app.norutin.model.request.EditDeskTaskTypeRequest
import com.app.norutin.model.request.GetDeskTaskTypesRequest
import java.util.*

interface TaskTypeService {
    fun createDefault(deskValueEntity: DeskValueEntity): MutableList<TaskTypeEntity>
    fun getForDesk(getDeskTaskTypesRequest: GetDeskTaskTypesRequest): List<TaskType>
    fun create(createDeskTaskTypeRequest: CreateDeskTaskTypeRequest): TaskType
    fun edit(editDeskTaskTypeRequest: EditDeskTaskTypeRequest): TaskType
    fun delete(id: Int): Optional<Int>
    fun deleteAll(deskValueId: Int)
}