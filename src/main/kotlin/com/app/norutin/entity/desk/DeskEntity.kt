package com.app.norutin.entity.desk

import com.app.norutin.entity.AbstractEntity
import com.app.norutin.entity.user.UserEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "desk")
open class DeskEntity (
    id: Int?,

    @Column(name = "name")
    open var name: String,

    @Column(name = "create_dt")
    open var createDate: Date,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    open var user: UserEntity?
) : AbstractEntity(id)
