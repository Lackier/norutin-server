package com.app.norutin.entity

import javax.persistence.*

@Entity
@Table(name = "priority_type")
open class PriorityTypeEntity(
    id: Int?,

    @Column(name = "name")
    open val name: String,

    @ManyToOne
    @JoinColumn(name = "desk_value_id", referencedColumnName = "id", nullable = false)
    open var deskValue: DeskValueEntity
) : AbstractEntity(id)
