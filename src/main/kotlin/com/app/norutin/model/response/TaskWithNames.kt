package com.app.norutin.model.response

import com.app.norutin.model.Task
import java.util.*

class TaskWithNames(
    override val id: Int?,
    override var name: String,
    override var createDate: Date?,
    override var doneDate: Date?,
    override var commitDate: Date?,
    override var deskId: Int,
    override var description: String?,
    override var doneOnTime: Boolean,
    override var statusId: Int?,
    override var typeId: Int?,
    override var priorityId: Int?,

    var status: String?,
    var type: String?,
    var priority: String?
) : Task(id, name, createDate, doneDate, commitDate, deskId, description, doneOnTime, statusId, typeId, priorityId)
