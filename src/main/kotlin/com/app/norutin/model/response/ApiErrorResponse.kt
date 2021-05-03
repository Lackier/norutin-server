package com.app.norutin.model.response

import org.springframework.http.HttpStatus

data class ApiErrorResponse(val status: HttpStatus, val errorCode: String) {
    var stackTrace: String? = null

    constructor(status: HttpStatus, errorCode: String, stackTrace: String) : this(status, errorCode) {
        this.stackTrace = stackTrace
    }
}