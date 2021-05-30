package com.app.norutin.service.impl

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.TaskStatusEntity
import com.app.norutin.mapper.TaskStatusMapper
import com.app.norutin.model.TaskStatus
import com.app.norutin.model.request.CreateDeskTaskStatusRequest
import com.app.norutin.model.request.EditDeskTaskStatusRequest
import com.app.norutin.model.request.GetDeskTaskStatusesRequest
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

        var priorityTypeEntity = taskStatusMapper.map(createDeskTaskStatusRequest, deskValue)
        priorityTypeEntity = taskStatusRepository.save(priorityTypeEntity)

        return taskStatusMapper.map(priorityTypeEntity)
    }

    override fun edit(editDeskTaskStatusRequest: EditDeskTaskStatusRequest): TaskStatus {
        var priorityTypeEntity = taskStatusRepository.findById(editDeskTaskStatusRequest.id).get()
        priorityTypeEntity.name = editDeskTaskStatusRequest.name

        priorityTypeEntity = taskStatusRepository.save(priorityTypeEntity)

        return taskStatusMapper.map(priorityTypeEntity)
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
}