package com.app.norutin.service.impl

import com.app.norutin.entity.settings.DeskValueEntity
import com.app.norutin.entity.settings.TaskStatusEntity
import com.app.norutin.mapper.TaskStatusMapper
import com.app.norutin.model.TaskStatus
import com.app.norutin.model.request.create.CreateDeskTaskStatusRequest
import com.app.norutin.model.request.edit.EditDeskTaskStatusRequest
import com.app.norutin.model.request.get.GetDeskTaskStatusesRequest
import com.app.norutin.repo.DeskValueRepository
import com.app.norutin.repo.TaskStatusRepository
import com.app.norutin.repo.def.TaskStatusDefRepository
import com.app.norutin.service.api.TaskStatusService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TaskStatusServiceImpl(
    private val taskStatusDefRepository: TaskStatusDefRepository,
    private val taskStatusRepository: TaskStatusRepository,
    private val deskValueRepository: DeskValueRepository
) : TaskStatusService {
    private val taskStatusMapper: TaskStatusMapper = Mappers.getMapper(TaskStatusMapper::class.java)

    override fun createDefault(deskValueEntity: DeskValueEntity): MutableList<TaskStatusEntity> {
        val defTaskStatuses = taskStatusDefRepository.findAll()

        val taskStatuses = defTaskStatuses.stream()
            .map { defTaskStatus -> taskStatusMapper.map(defTaskStatus, deskValueEntity) }
            .collect(Collectors.toList())

        return taskStatusRepository.saveAll(taskStatuses)
    }

    override fun getForDesk(getDeskTaskStatusesRequest: GetDeskTaskStatusesRequest): List<TaskStatus> {
        val deskValueId = deskValueRepository.getByDeskId(getDeskTaskStatusesRequest.deskId).getId()
        val taskStatusEntities = taskStatusRepository.getByDeskValueId(deskValueId!!)

        return taskStatusEntities.stream()
            .map { priorityTypeEntity -> taskStatusMapper.map(priorityTypeEntity) }
            .collect(Collectors.toList())
    }

    override fun create(createDeskTaskStatusRequest: CreateDeskTaskStatusRequest): TaskStatus {
        val deskValue = deskValueRepository.getByDeskId(createDeskTaskStatusRequest.deskId)

        var taskStatusEntity = taskStatusMapper.map(createDeskTaskStatusRequest, deskValue)
        taskStatusEntity = taskStatusRepository.save(taskStatusEntity)

        return taskStatusMapper.map(taskStatusEntity)
    }

    override fun edit(editDeskTaskStatusRequest: EditDeskTaskStatusRequest): TaskStatus {
        var taskStatusEntity = taskStatusRepository.findById(editDeskTaskStatusRequest.id).get()
        taskStatusEntity.name = editDeskTaskStatusRequest.name

        taskStatusEntity = taskStatusRepository.save(taskStatusEntity)

        return taskStatusMapper.map(taskStatusEntity)
    }

    override fun delete(id: Int): Optional<Int> {
        val taskStatusEntity = taskStatusRepository.findById(id)

        if (taskStatusEntity.isEmpty) {
            return Optional.empty()
        }

        taskStatusRepository.delete(taskStatusEntity.get())

        return Optional.of(id)
    }

    override fun deleteAll(deskValueId: Int) {
        val taskStatusEntities = taskStatusRepository.getByDeskValueId(deskValueId)

        taskStatusRepository.deleteAll(taskStatusEntities)
    }

    override fun find(statusId: Int): TaskStatusEntity {
        return taskStatusRepository.findById(statusId).get()
    }
}