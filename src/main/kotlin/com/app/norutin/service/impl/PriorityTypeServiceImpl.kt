package com.app.norutin.service.impl

import com.app.norutin.entity.DeskValueEntity
import com.app.norutin.entity.PriorityTypeEntity
import com.app.norutin.mapper.PriorityTypeMapper
import com.app.norutin.repo.PriorityTypeRepository
import com.app.norutin.repo.def.PriorityTypeDefRepository
import com.app.norutin.service.api.PriorityTypeService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class PriorityTypeServiceImpl(
    private val priorityTypeDefRepository: PriorityTypeDefRepository,
    private val priorityTypeRepository: PriorityTypeRepository
) : PriorityTypeService {
    private val priorityTypeMapper: PriorityTypeMapper = Mappers.getMapper(PriorityTypeMapper::class.java)

    override fun createDefault(deskValueEntity: DeskValueEntity): MutableList<PriorityTypeEntity> {
        val defPriorityTypes = priorityTypeDefRepository.findAll()

        val priorityTypes = defPriorityTypes.stream()
            .map { defPriorityType -> priorityTypeMapper.map(defPriorityType, deskValueEntity) }
            .collect(Collectors.toList())

        return priorityTypeRepository.saveAll(priorityTypes)
    }
}