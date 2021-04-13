package com.app.norutin.entity

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "users")
open class UserEntity(
    @Column(name = "name")
    open val name: String,

    @Column(name = "create_dt")
    open var createDate: Date,

    @Column(name = "uid")
    open val uid: String
) : AbstractEntity()