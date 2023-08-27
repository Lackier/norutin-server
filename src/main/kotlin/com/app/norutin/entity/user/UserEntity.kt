package com.app.norutin.entity.user

import com.app.norutin.entity.AbstractEntity
import com.app.norutin.entity.desk.DeskEntity
import javax.persistence.*

@Entity
@Table(name = "users")
open class UserEntity(
    id: Int?,

    val username: String,

    var password: String,

    val phone: String,

    val email: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: List<RoleEntity?>?,

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    open val desks: List<DeskEntity>?
) : AbstractEntity(id)