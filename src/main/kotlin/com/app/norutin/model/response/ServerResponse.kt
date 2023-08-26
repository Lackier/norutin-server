package com.app.norutin.model.response

class ServerResponse<T> {
    private var data: T? = null
    private var error: ApiError? = null

    constructor(data: T) {
        this.data = data
    }

    constructor(error: ApiError) {
        this.error = error
    }

    fun getData(): T? {
        return data
    }

    fun getError(): ApiError? {
        return error
    }
}