package com.app.norutin.model.response

import com.app.norutin.model.Desk
import lombok.Getter

@Getter
class GetDeskResponse {
    private var desk: Desk? = null
    private var error: ApiErrorResponse? = null

    constructor(desk: Desk) {
        this.desk = desk
    }

    constructor(error: ApiErrorResponse) {
        this.error = error
    }
}