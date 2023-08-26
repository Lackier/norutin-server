package com.app.norutin.entity.settings

import com.app.norutin.entity.AbstractDeskSettingEntity
import javax.persistence.*

@Entity
@Table(name = "task_type")
open class TaskTypeEntity(
    id: Int?,
    name: String,
    deskValue: DeskValueEntity
) : AbstractDeskSettingEntity(id, name, deskValue)
