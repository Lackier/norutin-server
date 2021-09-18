package com.app.norutin.service.impl

import com.app.norutin.entity.settings.DeskValueEntity
import com.app.norutin.entity.settings.TaskTypeEntity
import com.app.norutin.mapper.TaskTypeMapper
import com.app.norutin.model.TaskType
import com.app.norutin.model.request.create.CreateDeskTaskTypeRequest
import com.app.norutin.model.request.edit.EditDeskTaskTypeRequest
import com.app.norutin.model.request.get.GetDeskTaskTypesRequest
import com.app.norutin.repo.DeskValueRepository
import com.app.norutin.repo.TaskTypeRepository
import com.app.norutin.repo.def.TaskTypeDefRepository
import com.app.norutin.service.api.TaskTypeService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TaskTypeServiceImpl(
    private val taskTypeDefRepository: TaskTypeDefRepository,
    private val taskTypeRepository: TaskTypeRepository,
    private val deskValueRepository: DeskValueRepository
) : TaskTypeService {
    private val taskTypeMapper: TaskTypeMapper = Mappers.getMapper(TaskTypeMapper::class.java)

    override fun createDefault(deskValueEntity: DeskValueEntity): MutableList<TaskTypeEntity> {
        val defTaskTypes = taskTypeDefRepository.findAll()

        val taskTypes = defTaskTypes.stream()
            .map { defTaskType -> taskTypeMapper.map(defTaskType, deskValueEntity) }
            .collect(Collectors.toList())

        return taskTypeRepository.saveAll(taskTypes)
    }

    override fun getForDesk(getDeskTaskTypesRequest: GetDeskTaskTypesRequest): List<TaskType> {
        val deskValueId = deskValueRepository.getByDeskId(getDeskTaskTypesRequest.deskId).getId()
        val taskTypeEntities = taskTypeRepository.getByDeskValueId(deskValueId!!)
            .sortedBy { i -> i.getId() }

        return taskTypeEntities.stream()
            .map { priorityTypeEntity -> taskTypeMapper.map(priorityTypeEntity) }
            .collect(Collectors.toList())
    }

    override fun create(createDeskTaskTypeRequest: CreateDeskTaskTypeRequest): TaskType {
        val deskValue = deskValueRepository.getByDeskId(createDeskTaskTypeRequest.deskId)

        var taskTypeEntity = taskTypeMapper.map(createDeskTaskTypeRequest, deskValue)
        taskTypeEntity = taskTypeRepository.save(taskTypeEntity)

        return taskTypeMapper.map(taskTypeEntity)
    }

    override fun edit(editDeskTaskTypeRequest: EditDeskTaskTypeRequest): TaskType {
        var taskTypeEntity = taskTypeRepository.findById(editDeskTaskTypeRequest.id).get()
        taskTypeEntity.name = editDeskTaskTypeRequest.name

        taskTypeEntity = taskTypeRepository.save(taskTypeEntity)

        return taskTypeMapper.map(taskTypeEntity)
    }

    override fun delete(id: Int): Optional<Int> {
        val taskTypeEntity = taskTypeRepository.findById(id)

        if (taskTypeEntity.isEmpty) {
            return Optional.empty()
        }

        taskTypeRepository.delete(taskTypeEntity.get())

        return Optional.of(id)
    }

    override fun deleteAll(deskValueId: Int) {
        val taskTypeEntities = taskTypeRepository.getByDeskValueId(deskValueId)

        taskTypeRepository.deleteAll(taskTypeEntities)
    }

    override fun find(typeId: Int): TaskTypeEntity {
        return taskTypeRepository.findById(typeId).get()
    }
}