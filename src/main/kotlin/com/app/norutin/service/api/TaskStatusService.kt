package com.app.norutin.service.api

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.TaskStatusEntity
import com.app.norutin.model.TaskStatus
import com.app.norutin.model.request.create.CreateDeskTaskStatusRequest
import com.app.norutin.model.request.edit.EditDeskTaskStatusRequest
import com.app.norutin.model.request.get.GetDeskTaskStatusesRequest
import java.util.*

interface TaskStatusService {
    fun createDefault(deskValueEntity: DeskValueEntity): MutableList<TaskStatusEntity>
    fun getForDesk(getDeskTaskStatusesRequest: GetDeskTaskStatusesRequest): List<TaskStatus>
    fun create(createDeskTaskStatusRequest: CreateDeskTaskStatusRequest): TaskStatus
    fun edit(editDeskTaskStatusRequest: EditDeskTaskStatusRequest): TaskStatus
    fun delete(id: Int): Optional<Int>
    fun deleteAll(deskValueId: Int)
}