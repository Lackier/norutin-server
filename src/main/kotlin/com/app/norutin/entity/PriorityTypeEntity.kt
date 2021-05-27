package com.app.norutin.entity

import javax.persistence.*

@Entity
@Table(name = "priority_type")
open class PriorityTypeEntity(
    id: Int?,
    name: String,
    deskValue: DeskValueEntity
) : AbstractDeskSettingEntity(id, name, deskValue)
