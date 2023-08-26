package com.app.norutin.model.request.edit

import java.util.*

class EditTaskRequest(
    var id: Int,
    var name: String,
    var doneDate: Date?,
    var commitDate: Date?,
    var description: String?,
    var statusId: Int?,
    var typeId: Int?,
    var priorityId: Int?
)