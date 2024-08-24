package com.pefacel.taskmanagementapp.tasks.domain

import com.pefacel.taskmanagementapp.tasks.data.TaskRepository
import com.pefacel.taskmanagementapp.tasks.data.database.entity.TaskEntity
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(taskEntity: TaskEntity) {
        taskRepository.deleteTask(taskEntity)
    }
}