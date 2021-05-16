package com.app.norutin.service.impl

import com.app.norutin.entity.DeskEntity
import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.mapper.PriorityTypeMapper
import com.app.norutin.mapper.TaskStatusMapper
import com.app.norutin.mapper.TaskTypeMapper
import com.app.norutin.repo.def.PriorityTypeDefRepository
import com.app.norutin.repo.def.TaskStatusDefRepository
import com.app.norutin.repo.def.TaskTypeDefRepository
import com.app.norutin.service.api.TaskSettingsService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TaskSettingsServiceImpl(
    private val taskTypeDefRepository: TaskTypeDefRepository,
    private val taskStatusDefRepository: TaskStatusDefRepository,
    private val priorityTypeDefRepository: PriorityTypeDefRepository
) : TaskSettingsService {
    private val taskTypeMapper: TaskTypeMapper = Mappers.getMapper(TaskTypeMapper::class.java)
    private val taskStatusMapper: TaskStatusMapper = Mappers.getMapper(TaskStatusMapper::class.java)
    private val priorityTypeMapper: PriorityTypeMapper = Mappers.getMapper(PriorityTypeMapper::class.java)

    override fun createDefaultSettings(desk: DeskEntity): DeskValueEntity {
        val deskValueEntity = DeskValueEntity(null, desk)

        val a = 0

        val defTaskTypes = taskTypeDefRepository.findAll()
        deskValueEntity.taskTypes = defTaskTypes.stream()
            .map { defTaskType -> taskTypeMapper.map(defTaskType) }
            .collect(Collectors.toList())

        val defTaskStatuses = taskStatusDefRepository.findAll()
        deskValueEntity.taskStatuses = defTaskStatuses.stream()
            .map { defTaskStatus -> taskStatusMapper.map(defTaskStatus) }
            .collect(Collectors.toList())

        val defPriorityTypes = priorityTypeDefRepository.findAll()
        deskValueEntity.priorityTypes = defPriorityTypes.stream()
            .map { defPriorityType -> priorityTypeMapper.map(defPriorityType) }
            .collect(Collectors.toList())

        return deskValueEntity
    }
}