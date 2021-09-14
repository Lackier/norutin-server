package com.app.norutin.entity.settings

import com.app.norutin.entity.AbstractEntity
import com.app.norutin.entity.desk.DeskEntity
import javax.persistence.*

@Entity
@Table(name = "desk_value")
open class DeskValueEntity(
    id: Int?,

    @ManyToOne
    @JoinColumn(name = "desk_id", referencedColumnName = "id")
    open var desk: DeskEntity,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_value_id")
    open var taskTypes: MutableList<TaskTypeEntity> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_value_id")
    open var priorityTypes: MutableList<PriorityTypeEntity> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_value_id")
    open var taskStatuses: MutableList<TaskStatusEntity> = mutableListOf()
) : AbstractEntity(id)