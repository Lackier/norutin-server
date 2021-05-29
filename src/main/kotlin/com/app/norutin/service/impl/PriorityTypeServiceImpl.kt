package com.app.norutin.service.impl

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.PriorityTypeEntity
import com.app.norutin.mapper.PriorityTypeMapper
import com.app.norutin.model.TaskPriority
import com.app.norutin.model.request.CreateDeskPriorityTypeRequest
import com.app.norutin.model.request.EditDeskPriorityTypeRequest
import com.app.norutin.model.request.GetDeskPriorityTypesRequest
import com.app.norutin.repo.DeskValueRepository
import com.app.norutin.repo.PriorityTypeRepository
import com.app.norutin.repo.def.PriorityTypeDefRepository
import com.app.norutin.service.api.PriorityTypeService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class PriorityTypeServiceImpl(
    private val priorityTypeDefRepository: PriorityTypeDefRepository,
    private val priorityTypeRepository: PriorityTypeRepository,
    private val deskValueRepository: DeskValueRepository
) : PriorityTypeService {
    private val priorityTypeMapper: PriorityTypeMapper = Mappers.getMapper(PriorityTypeMapper::class.java)

    override fun createDefault(deskValueEntity: DeskValueEntity): MutableList<PriorityTypeEntity> {
        val defPriorityTypes = priorityTypeDefRepository.findAll()

        val priorityTypes = defPriorityTypes.stream()
            .map { defPriorityType -> priorityTypeMapper.map(defPriorityType, deskValueEntity) }
            .collect(Collectors.toList())

        return priorityTypeRepository.saveAll(priorityTypes)
    }

    override fun getForDesk(getDeskPriorityTypesRequest: GetDeskPriorityTypesRequest): List<TaskPriority> {
        val deskValueId = deskValueRepository.getByDeskId(getDeskPriorityTypesRequest.deskId).getId()
        val priorityTypeEntities = priorityTypeRepository.getByDeskValueId(deskValueId!!)

        return priorityTypeEntities.stream()
            .map { priorityTypeEntity -> priorityTypeMapper.map(priorityTypeEntity) }
            .collect(Collectors.toList())
    }

    override fun create(createDeskPriorityTypeRequest: CreateDeskPriorityTypeRequest): TaskPriority {
        val deskValue = deskValueRepository.getByDeskId(createDeskPriorityTypeRequest.deskId)

        var priorityTypeEntity = priorityTypeMapper.map(createDeskPriorityTypeRequest, deskValue)
        priorityTypeEntity = priorityTypeRepository.save(priorityTypeEntity)

        return priorityTypeMapper.map(priorityTypeEntity)
    }

    override fun edit(editDeskPriorityTypeRequest: EditDeskPriorityTypeRequest): TaskPriority {
        var priorityTypeEntity = priorityTypeRepository.findById(editDeskPriorityTypeRequest.id).get()
        priorityTypeEntity.name = editDeskPriorityTypeRequest.name

        priorityTypeEntity = priorityTypeRepository.save(priorityTypeEntity)

        return priorityTypeMapper.map(priorityTypeEntity)
    }

    override fun delete(id: Int): Optional<Int> {
        val priorityTypeEntity = priorityTypeRepository.findById(id)

        if (priorityTypeEntity.isEmpty) {
            return Optional.empty()
        }

        priorityTypeRepository.delete(priorityTypeEntity.get())

        return Optional.of(id)
    }

    override fun deleteAll(deskValueId: Int) {
        val priorityTypeEntities = priorityTypeRepository.getByDeskValueId(deskValueId)

        priorityTypeRepository.deleteAll(priorityTypeEntities)
    }
}