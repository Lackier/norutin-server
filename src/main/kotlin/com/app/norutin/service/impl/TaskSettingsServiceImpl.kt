package com.app.norutin.service.impl

import com.app.norutin.entity.DeskEntity
import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.repo.DeskValueRepository
import com.app.norutin.service.api.PriorityTypeService
import com.app.norutin.service.api.TaskSettingsService
import com.app.norutin.service.api.TaskStatusService
import com.app.norutin.service.api.TaskTypeService
import org.springframework.stereotype.Service

@Service
class TaskSettingsServiceImpl(
    private val deskValueRepository: DeskValueRepository,
    private val taskTypeService: TaskTypeService,
    private val taskStatusService: TaskStatusService,
    private val priorityTypeService: PriorityTypeService
) : TaskSettingsService {
    override fun createDefaultSettings(desk: DeskEntity): DeskValueEntity {
        val deskValueEntity = deskValueRepository.save(DeskValueEntity(null, desk))

        deskValueEntity.taskTypes = taskTypeService.createDefault(deskValueEntity)
        deskValueEntity.taskStatuses = taskStatusService.createDefault(deskValueEntity)
        deskValueEntity.priorityTypes = priorityTypeService.createDefault(deskValueEntity)

        return deskValueEntity
    }
}