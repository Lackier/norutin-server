package com.app.norutin.service.impl

import com.app.norutin.mapper.TaskMapper
import com.app.norutin.model.Task
import com.app.norutin.model.request.create.CreateTaskRequest
import com.app.norutin.model.request.edit.EditTaskRequest
import com.app.norutin.model.response.TaskWithNames
import com.app.norutin.repo.TaskRepository
import com.app.norutin.service.api.*
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of
import java.util.stream.Collectors


@Service
class TaskServiceImpl(
    private val taskRepository: TaskRepository,
    private val taskTypeService: TaskTypeService,
    private val taskStatusService: TaskStatusService,
    private val priorityTypeService: PriorityTypeService,
    private val deskService: DeskService
) : TaskService {
    private val taskMapper: TaskMapper = Mappers.getMapper(TaskMapper::class.java)

    override fun getDeskTasks(deskId: Int): List<Task> {
        val taskEntities = taskRepository.findByDeskId(deskId)

        return taskEntities.stream()
            .map { taskEntity -> taskMapper.map(taskEntity) }
            .collect(Collectors.toList())
    }

    override fun getTask(id: Int): Optional<Task> {
        val taskEntity = taskRepository.findById(id)
        if (taskEntity.isEmpty) {
            return empty()
        }

        return of(taskMapper.map(taskEntity.get()))
    }

    override fun create(createTaskRequest: CreateTaskRequest): Task {
        val newTask = taskMapper.map(createTaskRequest)
        newTask.createDate = Date()

        var taskEntity = taskMapper.map(newTask)
        taskEntity.desk = deskService.find(newTask.deskId)
        taskEntity.status = taskStatusService.find(newTask.statusId!!)
        if (newTask.typeId != null) taskEntity.type = taskTypeService.find(newTask.typeId!!)
        if (newTask.priorityId != null) taskEntity.priority = priorityTypeService.find(newTask.priorityId!!)

        taskEntity = taskRepository.save(taskEntity)
        return taskMapper.map(taskEntity)
    }

    override fun edit(editTaskRequest: EditTaskRequest): Optional<Task> {
        val taskEntityOpt = taskRepository.findById(editTaskRequest.id)

        if (taskEntityOpt.isEmpty) {
            return empty()
        }

        val taskEntity = taskEntityOpt.get()
        taskEntity.name = editTaskRequest.name
        if (editTaskRequest.doneDate != null && editTaskRequest.doneDate!!.time != 0L) {
            taskEntity.doneDate = editTaskRequest.doneDate
        }
        if (editTaskRequest.commitDate != null && editTaskRequest.commitDate!!.time != 0L) {
            taskEntity.commitDate = editTaskRequest.commitDate
        }
        taskEntity.description = editTaskRequest.description!!
        taskEntity.status = taskStatusService.find(editTaskRequest.statusId!!)
        if (editTaskRequest.typeId != null) taskEntity.type = taskTypeService.find(editTaskRequest.typeId!!)
        if (editTaskRequest.priorityId != null) taskEntity.priority =
            priorityTypeService.find(editTaskRequest.priorityId!!)

        return of(taskMapper.map(taskRepository.save(taskEntity)))
    }

    override fun delete(id: Int): Optional<Int> {
        val taskEntity = taskRepository.findById(id)

        if (taskEntity.isEmpty) {
            return empty()
        }

        taskRepository.delete(taskEntity.get())

        return of(id)
    }

    override fun getDeskTasksWithNames(deskId: Int): List<TaskWithNames> {
        val taskEntities = taskRepository.findByDeskId(deskId)

        return taskEntities.stream()
            .map { taskEntity -> taskMapper.mapWithNames(taskEntity) }
            .collect(Collectors.toList())
    }
}