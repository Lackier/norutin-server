package com.app.norutin.model

import java.util.*

open class Task(
    open val id: Int?,
    open var name: String,
    open var createDate: Date?,
    open var doneDate: Date?,
    open var commitDate: Date?,
    open var deskId: Int,
    open var description: String?,
    open var doneOnTime: Boolean,
    open var statusId: Int?,
    open var typeId: Int?,
    open var priorityId: Int?
)