package com.app.norutin.service.impl

import com.app.norutin.mapper.DeskMapper
import com.app.norutin.mapper.UserMapper
import com.app.norutin.model.Desk
import com.app.norutin.model.User
import com.app.norutin.model.request.create.CreateDeskRequest
import com.app.norutin.model.request.edit.EditDeskRequest
import com.app.norutin.repo.DeskRepository
import com.app.norutin.repo.UserRepository
import com.app.norutin.service.api.DeskService
import com.app.norutin.service.api.DeskSettingsService
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of
import java.util.stream.Collectors

@Service
class DeskServiceImpl(
    private val deskRepository: DeskRepository,
    private val userRepository: UserRepository,
    private val deskSettingsService: DeskSettingsService
) : DeskService {
    private val deskMapper: DeskMapper = Mappers.getMapper(DeskMapper::class.java)
    private val userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

    override fun getUserDesks(user: User): List<Desk> {
        val userEntity = userMapper.map(user)

        val deskEntities = deskRepository.findByUser(userEntity)

        return deskEntities.stream()
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

    override fun create(createDeskRequest: CreateDeskRequest, userId: Int): Desk {
        val newDesk = deskMapper.map(createDeskRequest)
        newDesk.userId = userId
        newDesk.createDate = Date()

        val newDeskEntity = deskMapper.map(newDesk)
        newDeskEntity.user = userRepository.findById(userId).get()
        deskRepository.save(newDeskEntity)

        deskSettingsService.createSettings(newDeskEntity)
        if (createDeskRequest.fillDefaultSettings) {
            deskSettingsService.createDefaultSettings(newDeskEntity.getId()!!)
        }

        return deskMapper.map(newDeskEntity)
    }

    override fun edit(editDeskRequest: EditDeskRequest, userId: Int): Desk {
        val editDeskEntity = deskRepository.findById(editDeskRequest.id).get()
        editDeskEntity.name = editDeskRequest.name
        deskRepository.save(editDeskEntity)

        return deskMapper.map(editDeskEntity)
    }

    override fun delete(deskId: Int): Optional<Int> {
        val deskEntity = deskRepository.findById(deskId)

        if (deskEntity.isEmpty) {
            return empty()
        }

        deskSettingsService.deleteAll(deskEntity.get().getId()!!)

        deskRepository.delete(deskEntity.get())

        return of(deskId)
    }
}