package com.app.norutin.service.impl

import com.app.norutin.mapper.UserMapper
import com.app.norutin.model.User
import com.app.norutin.repo.UserRepository
import com.app.norutin.service.api.UserService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    private val userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

    override fun findByUid(oid: String): User? {
        val userEntity = userRepository.findByOid(oid) ?: return null

        return userMapper.map(userEntity)
    }

    override fun save(user: User): User {
        if (user.createDate == null) {
            user.createDate = Date()
        }

        val userToSave = userMapper.map(user)

        val saved = userRepository.save(userToSave)

        return userMapper.map(saved)
    }
}
