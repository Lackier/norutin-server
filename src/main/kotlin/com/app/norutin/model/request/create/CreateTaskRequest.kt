package com.app.norutin.model.request.create

import java.util.*

class CreateTaskRequest(
    var name: String,
    var doneDate: Date?,
    var commitDate: Date?,
    var deskId: Int,
    var description: String?,
    var statusId: Int?,
    var typeId: Int?,
    var priorityId: Int?
)