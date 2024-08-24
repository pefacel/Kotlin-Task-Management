package com.pefacel.taskmanagementapp.tasks.domain

import com.pefacel.taskmanagementapp.tasks.data.TaskRepository
import com.pefacel.taskmanagementapp.tasks.data.database.entity.TaskEntity
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {


    suspend operator fun invoke(): List<TaskEntity> {

        var tasksFromDatabase = taskRepository.getTasksFromDatabase()

        println("0. tasksFromDatabase -> ")
        println(tasksFromDatabase)

        if (tasksFromDatabase.isEmpty()) {
            tasksFromDatabase = taskRepository.saveTasksOnDatabase()
        }

        println("1. tasksFromDatabase -> ")
        println(tasksFromDatabase)

        return tasksFromDatabase

    }


}