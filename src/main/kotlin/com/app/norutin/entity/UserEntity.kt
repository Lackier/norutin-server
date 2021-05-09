package com.app.norutin.entity

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "users")
open class UserEntity(
    id: Int?,

    @Column(name = "name")
    open val name: String,

    @Column(name = "create_dt")
    open var createDate: Date,

    @Column(name = "uid")
    open val uid: String,

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    open val desks: List<DeskEntity>?
) : AbstractEntity(id)