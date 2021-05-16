package com.app.norutin.entity

import javax.persistence.*

@Entity
@Table(name = "task")
open class TaskEntity(
    id: Int?,

    @Column(name = "name")
    open val name: String,

    @ManyToOne
    @JoinColumn(name = "desk_id", referencedColumnName = "id")
    open var user: DeskEntity?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "type_id")
    open var type: TaskTypeEntity?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "priority_id")
    open var priority: PriorityTypeEntity?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "status_id")
    open var status: TaskStatusEntity?
) : AbstractEntity(id)
