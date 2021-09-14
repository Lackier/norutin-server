package com.app.norutin.entity.settings

import com.app.norutin.entity.AbstractDeskSettingEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "priority_type")
open class PriorityTypeEntity(
    id: Int?,
    name: String,
    deskValue: DeskValueEntity
) : AbstractDeskSettingEntity(id, name, deskValue)
