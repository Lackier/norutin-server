package com.app.norutin.entity.user

import com.app.norutin.entity.AbstractEntity
import com.app.norutin.entity.desk.DeskEntity
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

    @Column(name = "email")
    open val email: String,

    @Column(name = "phone_number")
    open val phoneNumber: String,

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    open val desks: List<DeskEntity>?
) : AbstractEntity(id)