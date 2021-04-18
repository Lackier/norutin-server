package com.app.norutin.controller

import com.app.norutin.mapper.UserMapper
import com.app.norutin.model.User
import com.app.norutin.model.request.CreateUserRequest
import com.app.norutin.service.api.UserService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserRecord
import lombok.extern.slf4j.Slf4j
import org.mapstruct.factory.Mappers
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URISyntaxException


@Slf4j
@RestController
@RequestMapping("/api/users")
@CrossOrigin
class UserController(
    private val userService: UserService
) {
    private val userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

    @PostMapping("/signup")
    @Throws(URISyntaxException::class)
    fun createAccount(createUserRequest: CreateUserRequest): ResponseEntity<String> {
        val request: UserRecord.CreateRequest = UserRecord.CreateRequest()
            .setEmail(createUserRequest.email)
            .setPassword(createUserRequest.password)
            .setPhoneNumber(createUserRequest.phoneNumber)
            .setDisplayName(createUserRequest.name)

        val userRecord: UserRecord
        try {
            userRecord = FirebaseAuth.getInstance().createUser(request)
        } catch (e: FirebaseAuthException) {
            return ResponseEntity.badRequest().body("Account already exists")
        }

        return try {
            val user = userMapper.map(createUserRequest)
            user.uid = userRecord.uid

            val result: User = userService.save(user)

            ResponseEntity.created(URI("/api/users/" + result.uid)).body(result.uid)
        } catch (e: Exception) {
            FirebaseAuth.getInstance().deleteUser(userRecord.uid)
            ResponseEntity.badRequest().body("Error creating account")
        }
    }
}