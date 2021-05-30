package com.app.norutin.mapper

import com.app.norutin.entity.UserEntity
import com.app.norutin.model.User
import com.app.norutin.model.request.create.CreateUserRequest
import org.mapstruct.*

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
interface UserMapper {
    fun map(entity: UserEntity): User

    fun map(account: User): UserEntity

    fun map(createUserRequest: CreateUserRequest): User
}