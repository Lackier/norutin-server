package com.app.norutin.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "task")
class TaskEntity(
    id: Int?,

    @Column(name = "name")
    open var name: String,

    @Column(name = "create_dt")
    open var createDate: Date,

    @Column(name = "done_dt")
    open var doneDate: Date,

    @Column(name = "commit_dt")
    open var commitDate: Date,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    open var desk: DeskEntity?,

    @Column(name = "description")
    open var description: String,

    @Column(name = "done_on_time")
    open var doneOnTime: Boolean,

    @Column(name = "status_id")
    open var statusId: Int,

    @Column(name = "type_id")
    open var typeId: Int,

    @Column(name = "priority_id")
    open var priorityId: Int
) : AbstractEntity(id)