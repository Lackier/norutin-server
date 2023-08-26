package com.app.norutin.service.impl

import com.app.norutin.mapper.UserMapper
import com.app.norutin.model.User
import com.app.norutin.model.request.SignUpRequest
import com.app.norutin.repo.RoleRepository
import com.app.norutin.repo.UserRepository
import com.app.norutin.service.api.UserService
import org.mapstruct.factory.Mappers
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService {
    private val userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

    override fun currentUser(): User? {
        val username = SecurityContextHolder.getContext().authentication.name!!

        return userMapper.map(userRepository.findByUsername(username)!!)
    }

    override fun getCurrentUserId(): Int {
        val username = SecurityContextHolder.getContext().authentication.name!!

        return userRepository.findByUsername(username)?.id!!
    }

    override fun findByUserName(username: String): User? {
        val userEntity = userRepository.findByUsername(username) ?: return null

        return userMapper.map(userEntity)
    }

    override fun register(signUpRequest: SignUpRequest): User {
        val userToSave = userMapper.map(signUpRequest)
        userToSave.roles = listOf(roleRepository.findByName("USER"))
        userToSave.password = passwordEncoder.encode(userToSave.password)

        val saved = userRepository.save(userToSave)

        return userMapper.map(saved)
    }
}
