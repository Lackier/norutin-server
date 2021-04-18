package com.app.norutin.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "desk")
open class DeskEntity (
    @Column(name = "name")
    open val name: String,

    @Column(name = "create_dt")
    open var createDate: Date,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    open val user: UserEntity
) : AbstractEntity()
