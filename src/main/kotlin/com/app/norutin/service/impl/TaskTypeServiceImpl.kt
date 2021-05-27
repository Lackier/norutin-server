package com.app.norutin.service.impl

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.TaskTypeEntity
import com.app.norutin.mapper.TaskTypeMapper
import com.app.norutin.repo.TaskTypeRepository
import com.app.norutin.repo.def.TaskTypeDefRepository
import com.app.norutin.service.api.TaskTypeService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TaskTypeServiceImpl(
    private val taskTypeDefRepository: TaskTypeDefRepository,
    private val taskTypeRepository: TaskTypeRepository
) : TaskTypeService {
    private val taskTypeMapper: TaskTypeMapper = Mappers.getMapper(TaskTypeMapper::class.java)

    override fun createDefault(deskValueEntity: DeskValueEntity): MutableList<TaskTypeEntity> {
        val defTaskTypes = taskTypeDefRepository.findAll()

        val taskTypes = defTaskTypes.stream()
            .map { defTaskType -> taskTypeMapper.map(defTaskType, deskValueEntity) }
            .collect(Collectors.toList())

        return taskTypeRepository.saveAll(taskTypes)
    }
}