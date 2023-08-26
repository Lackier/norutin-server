package com.app.norutin.entity.user

import javax.persistence.*

@Entity
@Table(name = "roles")
class RoleEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        val name: String
)