package com.app.norutin.model

import java.util.*

class Task(
    val id: Int?, var name: String, var createDate: Date?, var doneDate: Date?, var commitDate: Date?,
    var deskId: Int, var desctription: String?, var doneOnTime: Boolean,
    var statusId: Int?, var typeId: Int?, var priorityId: Int?
)