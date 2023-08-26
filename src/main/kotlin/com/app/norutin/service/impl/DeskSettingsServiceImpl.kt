package com.app.norutin.service.impl

import com.app.norutin.entity.desk.DeskEntity
import com.app.norutin.entity.settings.DeskValueEntity
import com.app.norutin.repo.DeskValueRepository
import com.app.norutin.service.api.PriorityTypeService
import com.app.norutin.service.api.DeskSettingsService
import com.app.norutin.service.api.TaskStatusService
import com.app.norutin.service.api.TaskTypeService
import org.springframework.stereotype.Service

@Service
class DeskSettingsServiceImpl(
    private val deskValueRepository: DeskValueRepository,
    private val taskTypeService: TaskTypeService,
    private val taskStatusService: TaskStatusService,
    private val priorityTypeService: PriorityTypeService
) : DeskSettingsService {
    override fun createDefaultSettings(deskId: Int) {
        val deskValueEntity = deskValueRepository.getByDeskId(deskId)

        deskValueEntity.taskTypes = taskTypeService.createDefault(deskValueEntity)
        deskValueEntity.taskStatuses = taskStatusService.createDefault(deskValueEntity)
        deskValueEntity.priorityTypes = priorityTypeService.createDefault(deskValueEntity)
    }

    override fun deleteAll(deskId: Int) {
        val deskValueEntity = deskValueRepository.getByDeskId(deskId)

        taskTypeService.deleteAll(deskValueEntity.id!!)
        taskStatusService.deleteAll(deskValueEntity.id!!)
        priorityTypeService.deleteAll(deskValueEntity.id!!)

        deskValueRepository.delete(deskValueEntity)
    }

    override fun createSettings(desk: DeskEntity): DeskValueEntity {
        return deskValueRepository.save(DeskValueEntity(null, desk))
    }
}