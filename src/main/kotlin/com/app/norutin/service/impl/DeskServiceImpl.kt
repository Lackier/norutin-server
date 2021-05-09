package com.app.norutin.service.impl

import com.app.norutin.mapper.DeskMapper
import com.app.norutin.mapper.UserMapper
import com.app.norutin.model.Desk
import com.app.norutin.model.User
import com.app.norutin.model.request.CreateDesksRequest
import com.app.norutin.repo.DeskRepository
import com.app.norutin.service.api.DeskService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of
import java.util.stream.Collectors

@Service
class DeskServiceImpl(private val deskRepository: DeskRepository) : DeskService {
    private val deskMapper: DeskMapper = Mappers.getMapper(DeskMapper::class.java)
    private val userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

    override fun getUserDesks(user: User): List<Desk> {
        val userEntity = userMapper.map(user)

        val desks = deskRepository.findByUser(userEntity)

        return desks.stream()
            .map { deskEntity -> deskMapper.map(deskEntity) }
            .collect(Collectors.toList())
    }

    override fun getDesk(id: Int): Optional<Desk> {
        val deskEntity = deskRepository.findById(id)
        if (deskEntity.isEmpty) {
            return empty()
        }

        return of(deskMapper.map(deskEntity.get()))
    }

    override fun create(createDeskRequest: CreateDesksRequest, userId: Int): Desk {
        val newDesk = deskMapper.map(createDeskRequest)
        newDesk.userId = userId
        newDesk.createDate = Date()

        val newDeskEntity = deskRepository.save(deskMapper.map(newDesk))

        return deskMapper.map(newDeskEntity)
    }
}