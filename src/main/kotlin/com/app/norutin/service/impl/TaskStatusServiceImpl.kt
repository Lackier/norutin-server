package com.app.norutin.service.impl

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.TaskStatusEntity
import com.app.norutin.mapper.TaskStatusMapper
import com.app.norutin.repo.TaskStatusRepository
import com.app.norutin.repo.def.TaskStatusDefRepository
import com.app.norutin.service.api.PriorityTypeService
import com.app.norutin.service.api.TaskStatusService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TaskStatusServiceImpl(
    private val taskStatusDefRepository: TaskStatusDefRepository,
    private val taskStatusRepository: TaskStatusRepository
) : TaskStatusService {
    private val taskStatusMapper: TaskStatusMapper = Mappers.getMapper(TaskStatusMapper::class.java)

    override fun createDefault(deskValueEntity: DeskValueEntity): MutableList<TaskStatusEntity> {
        val defTaskStatuses = taskStatusDefRepository.findAll()

        val taskStatuses = defTaskStatuses.stream()
            .map { defTaskStatus -> taskStatusMapper.map(defTaskStatus, deskValueEntity) }
            .collect(Collectors.toList())

        return taskStatusRepository.saveAll(taskStatuses)
    }
}