package com.app.norutin.service.impl

import com.app.norutin.mapper.DeskMapper
import com.app.norutin.mapper.UserMapper
import com.app.norutin.model.Desk
import com.app.norutin.model.User
import com.app.norutin.repo.DeskRepository
import com.app.norutin.service.api.DeskService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class DeskServiceImpl(private val deskRepository: DeskRepository) : DeskService {
    private val deskMapper: DeskMapper = Mappers.getMapper(DeskMapper::class.java)
    private val userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

    override fun getUserDesks(user: User): List<Desk> {
        val userEntity = userMapper.map(user)

        if (userEntity.desks == null) {
            return Collections.emptyList()
        }

        return userEntity.desks!!.stream()
            .map { deskEntity -> deskMapper.map(deskEntity) }
            .collect(Collectors.toList())
    }
}