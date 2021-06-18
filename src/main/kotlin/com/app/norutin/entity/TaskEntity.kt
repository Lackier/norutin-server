package com.app.norutin.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "task")
open class TaskEntity(
    id: Int?,

    @Column(name = "name")
    open var name: String,

    @Column(name = "create_dt")
    open var createDate: Date,

    @Column(name = "done_dt")
    open var doneDate: Date?,

    @Column(name = "commit_dt")
    open var commitDate: Date?,

    @ManyToOne
    @JoinColumn(name = "desk_id", referencedColumnName = "id")
    open var desk: DeskEntity?,

    @Column(name = "description")
    open var description: String,

    @Column(name = "done_on_time")
    open var doneOnTime: Boolean?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "status_id")
    open var status: TaskStatusEntity?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "type_id")
    open var type: TaskTypeEntity?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "priority_id")
    open var priority: PriorityTypeEntity?
) : AbstractEntity(id)