package com.app.norutin.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Authentication failed")
class AuthFailedException : RuntimeException()