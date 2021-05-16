package com.app.norutin.entity.def

import com.app.norutin.entity.AbstractEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "task_status_def")
open class TaskStatusDefEntity(
    id: Int,

    @Column(name = "name")
    open val name: String
) : AbstractEntity(id)