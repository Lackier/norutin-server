package com.app.norutin.service.impl

import com.app.norutin.mapper.TaskMapper
import com.app.norutin.model.Task
import com.app.norutin.repo.TaskRepository
import com.app.norutin.service.api.TaskService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TaskServiceImpl(
    private val taskRepository: TaskRepository
) : TaskService {
    private val taskMapper: TaskMapper = Mappers.getMapper(TaskMapper::class.java)

    override fun getDeskTasks(deskId: Int): List<Task> {
        val taskEntities = taskRepository.findByDeskId(deskId)

        return taskEntities.stream()
            .map { taskEntity -> taskMapper.map(taskEntity) }
            .collect(Collectors.toList())
    }
}