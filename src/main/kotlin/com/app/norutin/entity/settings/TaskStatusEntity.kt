package com.app.norutin.entity.settings

import com.app.norutin.entity.AbstractDeskSettingEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "task_status")
open class TaskStatusEntity(
    id: Int?,
    name: String,
    deskValue: DeskValueEntity
) : AbstractDeskSettingEntity(id, name, deskValue)
