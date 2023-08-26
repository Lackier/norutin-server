package com.app.norutin.entity.def

import com.app.norutin.entity.AbstractEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "priority_type_def")
open class PriorityTypeDefEntity(
    id: Int,

    @Column(name = "name")
    open val name: String
) : AbstractEntity(id)