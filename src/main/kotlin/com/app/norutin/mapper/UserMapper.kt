package com.app.norutin.mapper

import com.app.norutin.entity.UserEntity
import com.app.norutin.model.User
import com.app.norutin.model.request.CreateUserRequest
import org.mapstruct.Mapper

@Mapper
interface UserMapper {
    fun map(entity: UserEntity): User
    fun map(account: User): UserEntity
    fun map(createUserRequest: CreateUserRequest): User
}