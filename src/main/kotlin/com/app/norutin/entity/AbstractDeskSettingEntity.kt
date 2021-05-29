package com.app.norutin.entity

import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractDeskSettingEntity(
    id: Int?,

    @Column(name = "name")
    open var name: String,

    @ManyToOne
    @JoinColumn(name = "desk_value_id", referencedColumnName = "id", nullable = false)
    open var deskValue: DeskValueEntity
) : AbstractEntity(id)