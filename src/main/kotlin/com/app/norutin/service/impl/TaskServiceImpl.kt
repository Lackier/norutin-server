package com.app.norutin.service.impl

import com.app.norutin.mapper.TaskMapper
import com.app.norutin.model.Task
import com.app.norutin.model.request.create.CreateTaskRequest
import com.app.norutin.model.request.edit.EditTaskRequest
import com.app.norutin.repo.TaskRepository
import com.app.norutin.service.api.TaskService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of
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

    override fun getTask(taskId: Int): Optional<Task> {
        val taskEntity = taskRepository.findById(taskId)
        if (taskEntity.isEmpty) {
            return empty()
        }

        return of(taskMapper.map(taskEntity.get()))
    }

    override fun create(createTaskRequest: CreateTaskRequest): Task {
        val newTask = taskMapper.map(createTaskRequest)
        newTask.createDate = Date()

        val taskEntity = taskRepository.save(taskMapper.map(newTask))
        return taskMapper.map(taskEntity)
    }

    override fun edit(editTaskRequest: EditTaskRequest): Optional<Task> {
        val taskEntityOpt = taskRepository.findById(editTaskRequest.id)

        if (taskEntityOpt.isEmpty) {
            return empty()
        }

        val taskEntity = taskEntityOpt.get()
        taskEntity.name = editTaskRequest.name
        taskEntity.doneDate = editTaskRequest.doneDate!!
        taskEntity.commitDate = editTaskRequest.commitDate!!
        taskEntity.description = editTaskRequest.description!!
        taskEntity.statusId = editTaskRequest.statusId!!
        taskEntity.typeId = editTaskRequest.typeId!!
        taskEntity.priorityId = editTaskRequest.priorityId!!

        return of(taskMapper.map(taskRepository.save(taskEntity)))
    }

    override fun delete(taskId: Int): Optional<Int> {
        val taskEntity = taskRepository.findById(taskId)

        if (taskEntity.isEmpty) {
            return empty()
        }

        taskRepository.delete(taskEntity.get())

        return of(taskId)
    }
}