package com.app.norutin.controller

import com.app.norutin.model.User
import com.app.norutin.model.request.LoginRequest
import com.app.norutin.model.request.SignUpRequest
import com.app.norutin.model.response.LoginResponse
import com.app.norutin.security.jwt.JwtTokenProvider
import com.app.norutin.service.api.UserService
import lombok.extern.slf4j.Slf4j
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@Slf4j
@RestController
@RequestMapping("/api/users")
@CrossOrigin
class UserController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): LoginResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password
            )
        )

        val user = userService.findByUserName(loginRequest.username)!!
        val roles = user.roles
        val token = jwtTokenProvider.createToken(loginRequest.username, roles)

        return LoginResponse(
            user,
            token
        )
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): User {
        return userService.register(signUpRequest);
    }

    @GetMapping("/profile")
    fun profile(): User {
        return userService.findByUserName(SecurityContextHolder.getContext().authentication.name)!!
    }
}