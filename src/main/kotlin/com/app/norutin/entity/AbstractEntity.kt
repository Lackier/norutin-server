package com.app.norutin.entity

import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") var id: Int? = null
) {

    /*open fun getId(): Int? {
        return id
    }*/

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
}