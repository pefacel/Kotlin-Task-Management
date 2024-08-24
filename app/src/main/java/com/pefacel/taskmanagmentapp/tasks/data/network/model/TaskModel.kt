package com.pefacel.taskmanagementapp.tasks.data.network.model

import java.util.UUID

data class TaskModel(
    val id: UUID,
    val content: String,
    val title: String,
    val creation_date: String,
    val due_date: String,
    val image: String,
    val isDone: Boolean,
)

