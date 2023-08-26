package com.app.norutin.mapper

import com.app.norutin.entity.user.RoleEntity
import com.app.norutin.entity.user.UserEntity
import com.app.norutin.model.Role
import com.app.norutin.model.User
import com.app.norutin.model.request.SignUpRequest
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.factory.Mappers

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
interface UserMapper {
    companion object {
        @JvmField
        val INSTANCE = Mappers.getMapper(UserMapper::class.java)
    }

    fun map(entity: UserEntity): User
    fun map(account: User): UserEntity
    fun map(signUpRequest: SignUpRequest): UserEntity

    fun map(entity: RoleEntity): Role
    fun map(role: Role): RoleEntity
}