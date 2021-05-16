package com.app.norutin.mapper

import com.app.norutin.entity.PriorityTypeEntity
import com.app.norutin.entity.def.PriorityTypeDefEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface PriorityTypeMapper {
    @Mapping(target = "id", ignore = true)
    fun map(defEntity: PriorityTypeDefEntity): PriorityTypeEntity
}